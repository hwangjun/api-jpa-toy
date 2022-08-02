package dev.backend.hw.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Response {
    protected final LocalDateTime timestamp = LocalDateTime.now();
    protected String status;
    protected int code;
    protected String requestUrl;
    protected String message;
}
