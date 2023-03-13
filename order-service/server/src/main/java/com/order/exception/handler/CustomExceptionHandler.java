package com.order.exception.handler;

import com.common.ConstraintInfo;
import com.common.ResponseInfo;
import com.common.ResultCode;
import com.common.StandardResponse;
import com.order.exception.InvalidOrderStatusStateException;
import com.order.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOrderStatusStateException.class)
    public StandardResponse invalidOrderStatusStateExceptionHandler(InvalidOrderStatusStateException ex) {
        String message = "Invalid order status state";
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNotFoundException.class)
    public StandardResponse orderNotFoundExceptionHandler(OrderNotFoundException ex) {
        String message = "Order not found in db";
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StandardResponse constraintHandle(MethodArgumentNotValidException ex) {
        Set<ConstraintInfo> constraints = ex.getBindingResult()
                .getAllErrors()
                .stream().map(this::toConstraintInfo)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.CONSTRAINT_VIOLATION, constraints));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public StandardResponse exceptionHandler(Exception ex) {
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, ex.getMessage(), ex.getMessage()));
    }

    private ConstraintInfo toConstraintInfo(ObjectError objectError) {
        ConstraintInfo result = null;
        if (objectError instanceof FieldError fieldError) {
            result = new ConstraintInfo();
            result.setParameter(fieldError.getField());
            String defaultMessage = objectError.getDefaultMessage();
            result.setMessage(defaultMessage);
            result.setSystemMessage(defaultMessage);
        }
        return result;
    }
}
