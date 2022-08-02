package dev.backend.hw.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchCondition implements Serializable {
    private String name;
    private String email;
}
