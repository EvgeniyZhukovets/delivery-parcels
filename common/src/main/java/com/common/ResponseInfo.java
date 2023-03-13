package com.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo implements Serializable {

    private String resultCode = ResultCode.OK.getValue();

    private String id;

    private String message;

    private String systemMessage;

    private Set<ConstraintInfo> constraints;

    private ResponseInfo(ResultCode resultCode, String id, String message, String systemMessage,
                        Set<ConstraintInfo> constraints) {
        this.resultCode = Optional.ofNullable(resultCode).map(ResultCode::getValue).orElse(null);
        this.id = id;
        this.message = message;
        this.systemMessage = systemMessage;
        this.constraints = constraints;
    }

    public static ResponseInfo getInstance() {
        return getInstance(ResultCode.OK, null, null, null);
    }

    public static ResponseInfo getInstance(ResultCode resultCode) {
        return getInstance(resultCode, null, null, null);
    }

    public static ResponseInfo getInstance(ResultCode resultCode, Set<ConstraintInfo> constraints) {
        return getInstance(resultCode, null, null, constraints);
    }

    public static ResponseInfo getInstance(ResultCode resultCode, String message, String systemMessage) {
        return new ResponseInfo(resultCode, UUID.randomUUID().toString(), message, systemMessage, null);
    }

    public static ResponseInfo getInstance(ResultCode resultCode, String message, String systemMessage,
                                           Set<ConstraintInfo> constraints) {
        return new ResponseInfo(resultCode, UUID.randomUUID().toString(), message, systemMessage, constraints);
    }
}
