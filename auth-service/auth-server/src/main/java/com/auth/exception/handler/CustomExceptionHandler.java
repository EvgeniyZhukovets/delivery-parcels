package com.auth.exception.handler;

import com.auth.exception.*;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.common.ConstraintInfo;
import com.common.ResponseInfo;
import com.common.ResultCode;
import com.common.StandardResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserExistsException.class)
    public StandardResponse userExistsExceptionHandler(UserExistsException ex) {
        String message = "User already exists";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public StandardResponse userNotFoundExceptionHandler(UserNotFoundException ex) {
        String message = "User not found";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProcessingTokenException.class)
    public StandardResponse processingTokenExceptionHandler(ProcessingTokenException ex) {
        String message = "Error while processing token. User not found in DB";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.INVALID_TOKEN, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JWTVerificationException.class)
    public StandardResponse JWTVerificationExceptionHandler(JWTVerificationException ex) {
        String message = "Error while JWT verification";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.UNEXPECTED_ERROR, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidCredentialsException.class)
    public StandardResponse invalidCredentialsException(InvalidCredentialsException ex) {
        String message = "Invalid credentials";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.INVALID_CREDENTIALS, message, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(TokenExpiredException.class)
    public StandardResponse tokenExpiredException(TokenExpiredException ex) {
        String message = "TokenExpired";
        log.warn(message + " exception", ex);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.TOKEN_EXPIRED, message, ex.getMessage()));
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
