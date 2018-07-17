package com.clopeza.api.login.error;


import com.clopeza.api.login.common.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ExceptionTranslator {

    /**
     * Process validation errors
     *
     * @param fieldErrors to process
     * @return {@link ApiError} instance
     */
    private ApiError processFieldErrors(List<FieldError> fieldErrors) {
        ApiError dto = new ApiError(ApiErrorConstants.ERR_VALIDATION, HttpStatus.BAD_REQUEST.value());
        for (FieldError fieldError : fieldErrors) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append(fieldError.getCode());
            if (StringUtils.hasText(fieldError.getDefaultMessage())) {
                errorMessage.append(". ");
                errorMessage.append(fieldError.getDefaultMessage());
            }
            dto.add(fieldError.getObjectName(), fieldError.getField(), errorMessage.toString());
        }
        return dto;
    }

    /**
     * MethodArgumentNotValidException exception handler
     *
     * @return {@link ApiError} instance
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }


    /**
     * UsernameNotFoundException exception handler
     *
     * @param exception to process
     * @return {@link ApiError} instance
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError processEntityNotFound(UsernameNotFoundException exception) {
        return new ApiError(ApiErrorConstants.ERR_USER, exception.getMessage(),
                HttpStatus.NOT_FOUND.value());
    }

}
