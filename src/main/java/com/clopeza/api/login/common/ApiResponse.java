
package com.clopeza.api.login.common;

import java.util.Calendar;

/**
 * Generic REST API response
 */
public class ApiResponse<T> {

    private T responseData;

    private Long responseDate;

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public Long getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Long responseDate) {
        this.responseDate = responseDate;
    }


    /**
     * Constructor with data, and operation result
     *
     * @param responseData of the operation
     */
    public ApiResponse(T responseData) {
        super();
        this.responseData = responseData;
        this.responseDate = Calendar.getInstance().getTimeInMillis();
    }
}
