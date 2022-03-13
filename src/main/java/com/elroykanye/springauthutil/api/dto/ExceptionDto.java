package com.elroykanye.springauthutil.api.dto;

import lombok.*;

public class ExceptionDto {
    private String status;
    private String error;
    private String timestamp;
    private String message;
    private String explanation;
    private String path;

    public ExceptionDto() {

    }

    public ExceptionDto(String status, String error, String timestamp, String message, String explanation, String path) {
        this.status = status;
        this.error = error;
        this.timestamp = timestamp;
        this.message = message;
        this.explanation = explanation;
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
