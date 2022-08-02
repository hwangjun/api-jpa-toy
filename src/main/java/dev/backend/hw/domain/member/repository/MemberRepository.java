package dev.backend.hw.domain.member.repository;

import dev.backend.hw.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndPassword(String email, String password);
}
