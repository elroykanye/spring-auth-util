package com.elroykanye.springauthutil.exception.advice;

import com.elroykanye.springauthutil.api.dto.ExceptionDto;
import org.springframework.http.HttpStatus;

public class ExceptionUtil {
    public static ExceptionDto createExceptionResponseBody(Exception ex, HttpStatus httpStatus) {

        ExceptionDto exceptionDto =  new ExceptionDto();
        exceptionDto.setStatus(String.valueOf(httpStatus.value()));
        exceptionDto.setError(httpStatus.getReasonPhrase());
        exceptionDto.setTimestamp(String.valueOf(System.currentTimeMillis()));
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setExplanation(ex.getLocalizedMessage());
        exceptionDto.setPath(ex.getStackTrace()[0].getClassName());
        return exceptionDto;

    }
}
