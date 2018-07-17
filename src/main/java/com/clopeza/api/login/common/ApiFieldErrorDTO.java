package com.clopeza.api.login.common;

import java.io.Serializable;

/**
 * Error field validation error DTO
 */
public class ApiFieldErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String objectName;

    private final String field;

    private final String message;

    ApiFieldErrorDTO(String dto, String field, String message) {
        this.objectName = dto;
        this.field = field;
        this.message = message;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiFieldErrorDTO [objectName=" + objectName + ", field=" + field + ", message=" + message + "]";
    }

}
