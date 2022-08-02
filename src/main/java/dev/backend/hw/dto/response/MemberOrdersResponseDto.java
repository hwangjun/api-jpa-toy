package dev.backend.hw.dto.response;


import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "단일 회원 주문 목록 응답 DTO")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberOrdersResponseDto {

    @Schema(description = "주문번호", example = "A100")
    private String orderNumber;
    @Schema(description = "제품명", example = "맥북프로")
    private String itemName;
    @Schema(description = "결제일시", example = "2022-07-29T10:14:57.092782")
    private LocalDateTime orderDateTime;


    @QueryProjection
    public MemberOrdersResponseDto(String orderNumber, String itemName, LocalDateTime orderDateTime) {
        this.orderNumber = orderNumber;
        this.itemName = itemName;
        this.orderDateTime = orderDateTime;
    }
}
