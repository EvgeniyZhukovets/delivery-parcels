package com.delivery.exception.handler;

import com.common.ConstraintInfo;
import com.common.ResponseInfo;
import com.common.ResultCode;
import com.common.StandardResponse;
import com.delivery.exception.IllegalParcelStatusChangeException;
import com.delivery.exception.InvalidParcelStateException;
import com.delivery.exception.InvalidParcelStatusStateException;
import com.delivery.exception.ParcelNotFoundException;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalParcelStatusChangeException.class)
    public StandardResponse illegalParcelStatusChangeExceptionHandler(IllegalParcelStatusChangeException ex) {
        String message = "Illegal parcel status change";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParcelStateException.class)
    public StandardResponse userNotFoundExceptionHandler(InvalidParcelStateException ex) {
        String message = "User not found in db";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParcelStatusStateException.class)
    public StandardResponse processingTokenExceptionHandler(InvalidParcelStatusStateException ex) {
        String message = "Invalid parcel status state";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParcelNotFoundException.class)
    public StandardResponse JWTVerificationExceptionHandler(ParcelNotFoundException ex) {
        String message = "Parcel not found in db";
        log.warn(message + " exception", ex);
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
        log.warn("Unhandled exception", ex);
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
