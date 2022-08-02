package dev.backend.hw.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse extends Response {
    private List<Error> errorList;

    @Builder
    public ErrorResponse(String status, int code, String requestUrl, String message, List<Error> errorList) {
        this.status = status;
        this.code = code;
        this.requestUrl = requestUrl;
        this.message = message;
        this.errorList = errorList;
    }
}
