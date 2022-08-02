package dev.backend.hw.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {
    private String key;
    private String operation;
    private Object value;

    public Search() {
    }

    public Search(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
