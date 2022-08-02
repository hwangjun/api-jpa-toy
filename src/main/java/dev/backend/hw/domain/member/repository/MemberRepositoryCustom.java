package dev.backend.hw.domain.member.repository;

import dev.backend.hw.dto.response.MemberListResponseDto;
import dev.backend.hw.dto.response.MemberOrdersResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberOrdersResponseDto> findMemberOrders(Long memberId);
    Page<MemberListResponseDto> findMemberList(String name, String email, Pageable pageable);
}
