package dev.backend.hw.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import dev.backend.hw.domain.member.type.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "회원 목록 조회(마지막 주문정보 포함) 응답 DTO")
@Getter
@Setter
public class MemberListResponseDto {

    @Schema(description = "회원 ID")
    private Long memberId;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "별명")
    private String nickName;
    @Schema(description = "전화번호")
    private String phone;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "성별")
    private Gender gender;
    @Schema(description = "마지막 주문")
    private OrderDto order;

    @QueryProjection
    public MemberListResponseDto(Long memberId, String name, String nickName, String phone, String email, Gender gender, OrderDto order) {
        this.memberId = memberId;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.order = order;
    }
}
