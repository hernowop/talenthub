package com.talenthub.validator;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class Result {
    public Result(String message) {
        this.success = true;
        this.message = message;
    }

    public Result(String message, Object data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.message = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    private String message;
    private boolean success;

    private Object data;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
    @JsonProperty("data")
    public Object getObject(){
        return data;
    }


}
