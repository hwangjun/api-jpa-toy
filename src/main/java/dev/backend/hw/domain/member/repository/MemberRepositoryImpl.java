package dev.backend.hw.domain.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.backend.hw.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static dev.backend.hw.domain.member.entity.QMember.member;
import static dev.backend.hw.domain.order.entity.QOrder.order;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberOrdersResponseDto> findMemberOrders(Long memberId) {
        return queryFactory
                .select(new QMemberOrdersResponseDto(
                        order.orderNumber,
                        order.itemName,
                        order.orderDateTime
                ))
                .from(order)
                .where(order.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public Page<MemberListResponseDto> findMemberList(String name, String email, Pageable pageable) {
        List<MemberListResponseDto> content = queryFactory
                .select(new QMemberListResponseDto(
                        member.id,
                        member.name.max(),
                        member.nickName.max(),
                        member.phone.max(),
                        member.email.max(),
                        member.gender.max(),
                        new QOrderDto(
                                order.id.max(),
                                order.orderNumber.max(),
                                order.itemName.max(),
                                order.orderDateTime.max()
                        )
                ))
                .from(order)
                .leftJoin(order.member, member)
                .where(
                        nameEq(name),
                        emailEq(email)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(member.id)
                .fetch();

        int totalSize = queryFactory
                .select(new QMemberListResponseDto(
                        member.id,
                        member.name.max(),
                        member.nickName.max(),
                        member.phone.max(),
                        member.email.max(),
                        member.gender.max(),
                        new QOrderDto(
                                order.id.max(),
                                order.orderNumber.max(),
                                order.itemName.max(),
                                order.orderDateTime.max()
                        )))
                .from(order)
                .join(order.member, member)
                .where(
                        nameEq(name),
                        emailEq(email)
                )
                .groupBy(member.id)
                .fetch().size();

        return new PageImpl<>(content, pageable, totalSize);
    }

    private BooleanExpression nameEq(String name) {
        return hasText(name) ? member.name.eq(name) : null;
    }

    private BooleanExpression emailEq(String email) {
        return hasText(email) ? member.email.eq(email) : null;
    }
}
