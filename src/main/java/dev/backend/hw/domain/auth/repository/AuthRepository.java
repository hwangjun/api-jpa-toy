package dev.backend.hw.domain.auth.repository;

import dev.backend.hw.config.security.dto.MemberPrincipal;
import dev.backend.hw.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Member, Long> {
}
