package dev.backend.hw.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResponseDto {

    private String email;

    @Builder
    public MemberLoginResponseDto(String email) {
        this.email = email;
    }
}
