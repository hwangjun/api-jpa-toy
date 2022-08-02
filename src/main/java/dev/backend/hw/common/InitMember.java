package dev.backend.hw.common;

import dev.backend.hw.domain.member.entity.Member;
import dev.backend.hw.domain.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.init();
    }

    @Service
    static class InitMemberService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            for (int i = 0; i < 11; i++) {
                String gender = i % 2 == 0 ? "MAN" : "WOMAN";
                Member member = Member.builder()
                        .name("member" + i)
                        .nickName("nick" + i)
                        .email("test" + i + "@test.com")
                        .password("aA1@00000" + i)
                        .phone("0100000000" + i)
                        .gender(gender)
                        .build();
                em.persist(member);

                Order order1 = Order.builder()
                        .itemName("item" + i)
                        .orderNumber("A10" + i)
                        .orderDateTime(LocalDateTime.now())
                        .member(member)
                        .build();
                em.persist(order1);

                Order order2 = Order.builder()
                        .itemName("item" + i)
                        .orderNumber("B10" + i)
                        .orderDateTime(LocalDateTime.now())
                        .member(member)
                        .build();
                em.persist(order2);
            }
        }
    }
}
