package dev.backend.hw.domain.member.dto;

import dev.backend.hw.common.Phone;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "회원가입 요청 DTO")
@Getter
@Setter
public class MemberJoinRequestDto {

    @Schema(example = "홍길동", description = "필수항목, 최대길이는 20, 한글, 영문 대소문자만 입력 가능합니다.")
    @Parameter(name = "이름", required = true)
    @NotBlank(message = "이름을 입력해주세요.")
    @Length(max = 20, message = "최대길이는 20입니다.")
    @Pattern(regexp = "[가-힣a-zA-Z]*", message = "한글, 영문 대소문자만 입력 가능합니다.")
    private String name;

    @Schema(example = "honggildong", description = "필수항목, 최대길이는 30, 영문 소문자만 입력 가능합니다.")
    @Parameter(name = "별명", required = true)
    @NotBlank(message = "별명을 입력해주세요.")
    @Length(max = 30, message = "최대길이는 30입니다.")
    @Pattern(regexp = "[a-z]*", message = "영문 소문자만 입력 가능합니다.")
    private String nickName;

    @Schema(example = "**********",
            description = "필수항목, 최소 10자 이상, 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여 입력 가능합니다.")
    @Parameter(name= "비밀번호", required = true)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Length(min = 10, message = "최소 10자 이상입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{10,}$",
            message = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여 최소 10자이상 입니다.")
    private String password;

    @Schema(example = "01012345678",
            description = "필수항목, 최대 길이는 20, 숫자만 입력 가능합니다.")
    @Parameter(name = "전화번호", required = true)
    @Phone
    private String phone;

    @Schema(example = "honggildong@example.com",
            description = "필수항목, 최대 길이는 100, 이메일 형식에 맞게 입력 가능합니다.")
    @Parameter(name = "이메일", required = true)
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Length(max = 100, message = "최대 길이는 100 입니다.")
    private String email;

    @Schema(example = "MAN",
            description = "MAN(남자) 또는 WOMAN(여자)를 선택해주세요.")
    @Parameter(name = "성별", required = false)
    private String gender;

    @Builder
    public MemberJoinRequestDto(String name, String nickName, String password, String phone, String email, String gender) {
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }
}
