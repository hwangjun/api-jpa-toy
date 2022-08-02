package dev.backend.hw.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Long orderId;
    private String orderNumber;
    private String itemName;
    private LocalDateTime orderDateTime;

    @QueryProjection
    public OrderDto(Long orderId, String orderNumber, String itemName, LocalDateTime orderDateTime) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.itemName = itemName;
        this.orderDateTime = orderDateTime;
    }
}
