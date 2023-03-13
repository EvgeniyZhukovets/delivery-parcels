package com.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConstraintInfo implements Serializable {

    private String parameter;
    private String message;
    private String systemMessage;
}
