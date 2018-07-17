package com.clopeza.api.login.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DTO for transferring error message with a list of field errors.
 */
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long timestamp;

    private final Integer status;

    private final String error;

    private final String description;

    private List<ApiFieldErrorDTO> fieldErrors;

    /**
     * Protected default constructor to allow JAXB marshalling.
     */
    protected ApiError() {
        this.error = null;
        this.description = null;
        this.status = null;
        this.timestamp = Calendar.getInstance().getTimeInMillis();
    }

    public ApiError(String message) {
        this(message, null, null);
    }

    public ApiError(String message, Integer status) {
        this(message, null, status);
    }

    public ApiError(String message, String description) {
        this.error = message;
        this.description = description;
        this.status = null;
        this.timestamp = Calendar.getInstance().getTimeInMillis();

    }

    public ApiError(String message, String description, Integer status) {
        this.error = message;
        this.description = description;
        this.status = status;
        this.timestamp = Calendar.getInstance().getTimeInMillis();

    }

    public ApiError(String message, String description, List<ApiFieldErrorDTO> fieldErrors, Integer status) {
        this.error = message;
        this.description = description;
        this.fieldErrors = fieldErrors;
        this.status = status;
        this.timestamp = Calendar.getInstance().getTimeInMillis();

    }

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new ApiFieldErrorDTO(objectName, field, message));
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }

    public List<ApiFieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ApiError [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", description="
                + description + ", fieldErrors=" + fieldErrors + "]";
    }

}
