package dev.backend.hw.domain.member.service;

import dev.backend.hw.domain.member.dto.MemberJoinRequestDto;
import dev.backend.hw.domain.member.dto.MemberLoginRequestDto;
import dev.backend.hw.dto.response.*;
import dev.backend.hw.domain.member.entity.Member;
import dev.backend.hw.exception.MemberEmailAlreadyExistsException;
import dev.backend.hw.exception.MemberNotFoundException;
import dev.backend.hw.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    public MemberJoinResponseDto join(MemberJoinRequestDto memberJoinDto) {
//        // 이메일 중복여부 체크
//        validateDuplicated(memberJoinDto.getEmail());
//
//        Member newMember = Member.builder()
//                .name(memberJoinDto.getName())
//                .nickName(memberJoinDto.getNickName())
//                .password(passwordEncoder.encode(memberJoinDto.getPassword()))
//                .phone(memberJoinDto.getPhone())
//                .email(memberJoinDto.getEmail())
//                .gender(memberJoinDto.getGender())
//                .build();
//        Member saveMember = memberRepository.save(newMember);
//
//        return new MemberJoinResponseDto(saveMember.getEmail());
//    }

//    public MemberLoginResponseDto login(MemberLoginRequestDto memberLoginRequestDto) {
//        Member member = memberRepository.findByEmailAndPassword(memberLoginRequestDto.getEmail(),
//                        passwordEncoder.encode(memberLoginRequestDto.getPassword()))
//                .orElseThrow(() -> new MemberNotFoundException("이메일 또는 비밀번호를 잘못 입력했습니다."));
//
//        return MemberLoginResponseDto.builder()
//                .email(member.getEmail())
//                .build();
//
//    }

    public List<MemberOrdersResponseDto> findMemberOrders(Long memberId) {
        // 회원 정보 있는지 확인 후 주문건 조회
        Member member = getMember(memberId);
        return memberRepository.findMemberOrders(memberId);
    }

    public MemberDetailResponseDto findMemberDetail(Long memberId) {
        Member member = getMember(memberId);

        return MemberDetailResponseDto.builder()
                .name(member.getName())
                .nickName(member.getNickName())
                .phone(member.getPhone())
                .email(member.getEmail())
                .gender(member.getGender())
                .build();
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원의 정보가 없습니다."));
    }

    public Page<MemberListResponseDto> findMemberList(String name, String email, Pageable pageable) {
        return memberRepository.findMemberList(name, email, pageable);
    }

    public void validateDuplicated(String email) {
        if (memberRepository.findByEmail(email).isPresent())
            throw new MemberEmailAlreadyExistsException("해당 이메일 주소는 이미 존재합니다.");
    }


}
