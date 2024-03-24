package br.com.dev.cliente.dto;

import java.time.Instant;

public class CustomError {
    private Instant timesamp;
    private Integer status;
    private String error;
    private String path;

    public CustomError(Instant timesamp, Integer status, String error, String path) {
        this.timesamp = timesamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public Instant getTimesamp() {
        return timesamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}