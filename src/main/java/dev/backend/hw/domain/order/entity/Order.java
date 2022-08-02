package dev.backend.hw.domain.order.entity;


import dev.backend.hw.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

/**
 * 주문
 */

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long id;

    //주문번호(길이: 12, 필수여부: 필수, 제약사항: 중복이 불가능한 임의의 영문 대문자, 숫자 조합)
    @Column(length = 12, nullable = false, unique = true)
    private String orderNumber;

    //제품명(길이: 100, 필수여부: 필수, 제약사항: emoji 를 포함한 모든 문자)
    @Column(length = 100, nullable = false)
    private String itemName;

    //결제일시(Datetime, 필수여부: 필수, 제약사항: Timezone 을 고려한 시간 정보)
    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Order(String orderNumber, String itemName, LocalDateTime orderDateTime, Member member) {
        this.orderNumber = orderNumber;
        this.itemName = itemName;
        this.orderDateTime = orderDateTime;
        this.member = member;
    }
}
