package dev.backend.hw.common;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    private String field;
    private String message;
    private String invalidValue;

    @Builder
    public Error(String field, String message, String invalidValue) {
        this.field = field;
        this.message = message;
        this.invalidValue = invalidValue;
    }
}
