package dev.backend.hw.domain.member.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "회원 로그인 요청 DTO")
@Getter
@Setter
@NoArgsConstructor
public class MemberLoginRequestDto {

    @Schema(example = "honggildong@example.com",
            description = "필수항목, 최대 길이는 100, 이메일 형식에 맞게 입력 가능합니다.")
    @Parameter(name = "이메일", required = true)
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Length(max = 100, message = "최대 길이는 100 입니다.")
    private String email;

    @Schema(example = "**********",
            description = "필수항목, 최소 10자 이상, 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여 입력 가능합니다.")
    @Parameter(name= "비밀번호", required = true)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Length(min = 10, message = "최소 10자 이상입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{10,}$",
            message = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여 최소 10자이상 입니다.")
    private String password;

    public MemberLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
