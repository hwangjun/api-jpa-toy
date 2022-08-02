package dev.backend.hw.domain.member.entity;

import dev.backend.hw.domain.member.type.Gender;
import dev.backend.hw.domain.member.type.UserRole;
import dev.backend.hw.domain.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 회원
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    //이름(길이: 20, 필수여부 : 필수, 제약사항: 한글,영문 대소문자만 허용)
    @Column(length = 20, nullable = false)
    private String name;

    //별명(길이: 30, 필수여부 : 필수, 제약사항: 영문 소문자만 허용)
    @Column(length = 30, nullable = false)
    private String nickName;

    //비밀번호(길이: 최소10자이상, 필수여부 : 필수, 제약사항: 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함)
    @Column(nullable = false)
    private String password;

    //전화번호(길이: 20, 필수여부 : 필수, 제약사항: 숫자)
    @Column(length = 20, nullable = false)
    private String phone;

    //이름(길이: 100, 필수여부 : 필수, 제약사항: 이메일 형식)
    @Column(length = 100, nullable = false)
    private String email;

    //성별(필수여부 : 선택)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @CollectionTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @Builder
    public Member(String name, String nickName, String password, String phone, String email, String gender) {
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = Gender.convertFrom(gender);
        this.roles = Stream.of(UserRole.USER)
                .collect(Collectors.toSet());
    }
}
