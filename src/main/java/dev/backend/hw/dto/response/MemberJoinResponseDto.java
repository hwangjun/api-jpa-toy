package dev.backend.hw.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "회원가입 응답 DTO")
@Getter
@Setter
public class MemberJoinResponseDto {
    @Schema(description = "이메일", example = "honggildong@example.com")
    private String email;

    public MemberJoinResponseDto(String email) {
        this.email = email;
    }
}
