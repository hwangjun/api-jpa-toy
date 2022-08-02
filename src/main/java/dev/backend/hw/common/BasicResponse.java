package dev.backend.hw.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicResponse<T> {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String status;
    private int code;
    private String requestUrl;
    private String message;
    private T data;
    private List<Error> errorList;

    public BasicResponse(final String status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public BasicResponse(final String status, final int code, final String message, final String requestUrl, final T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.requestUrl = requestUrl;
        this.data = data;
    }

    @Builder
    public BasicResponse(final String status, final int code, final String message, final String requestUrl, final T data, final List<Error> errorList) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.requestUrl = requestUrl;
        this.data = data;
        this.errorList = errorList;
    }

    public static <T> BasicResponse<T> success(final String status, final int code, final String message, final String requestUrl, final T t) {
        return BasicResponse.<T>builder()
                .status(status)
                .code(code)
                .message(message)
                .requestUrl(requestUrl)
                .data(t)
                .build();
    }

    public static <T> BasicResponse<T> error(final String status, final int code, final String message, final String requestUrl, final List<Error> errorList) {
        return BasicResponse.<T>builder()
                .status(status)
                .code(code)
                .message(message)
                .requestUrl(requestUrl)
                .errorList(errorList)
                .build();
    }
}
