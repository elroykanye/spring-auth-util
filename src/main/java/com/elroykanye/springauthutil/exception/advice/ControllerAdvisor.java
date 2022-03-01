package com.elroykanye.springauthutil.exception.advice;

import com.elroykanye.springauthutil.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {
    /**
     * Handles resolving the exception to a response entity.
     * @param ex The exception to be resolved.
     * @param httpStatus The http status to be used.
     * @return The response entity.
     */
    private ResponseEntity<Object> handler(Exception ex, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                ExceptionUtil.createExceptionResponseBody(ex, httpStatus),
                httpStatus
        );
    }

    // Exception Handlers

    /**
     * Handles credentials invalid exception.
     * @param ex The exception to be resolved.
     * @param webRequest The web request.
     * @return The response entity.
     */
    @ExceptionHandler(value = {
            UserException.UserCredentialsInvalidException.class
    })
    public ResponseEntity<Object> handleCredentialsInvalidException(Exception ex, WebRequest webRequest) {
        return handler(ex, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles user not found exception.
     * @param ex The exception to be resolved.
     * @param webRequest The web request.
     * @return The response entity.
     */

    @ExceptionHandler(value = {
            UserException.UserNotFoundWithIdException.class,
            UserException.UserNotFoundWithUsernameException.class
    })
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest webRequest) {
        return handler(ex, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles user already exists exception.
     * @param ex The exception to be resolved.
     * @param webRequest The web request.
     * @return The response entity.
     */
    @ExceptionHandler(value = {
            UserException.UserAlreadyExistsException.class,
    })
    public ResponseEntity<Object> handleAlreadyExistsException(Exception ex, WebRequest webRequest) {
        return handler(ex, HttpStatus.CONFLICT);
    }



}
