package dev.backend.hw.dto.response;

import dev.backend.hw.domain.member.type.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "회원 상세 정보 응답 DTO")
@Getter
@Setter
public class MemberDetailResponseDto {
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "별명", example = "honggildong")
    private String nickName;
    @Schema(description = "전화번호", example = "01012345678")
    private String phone;
    @Schema(description = "이메일", example = "honggildong@example.com")
    private String email;
    @Schema(description = "성별", example = "MAN")
    private Gender gender;

    @Builder
    public MemberDetailResponseDto(String name, String nickName, String phone, String email, Gender gender) {
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }
}
