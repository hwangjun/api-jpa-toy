package dev.backend.hw.domain.auth.service;

import dev.backend.hw.config.JwtTokenProvider;
import dev.backend.hw.config.security.dto.MemberPrincipal;
import dev.backend.hw.domain.auth.repository.AuthRepository;
import dev.backend.hw.domain.member.dto.MemberJoinRequestDto;
import dev.backend.hw.domain.member.dto.MemberLoginRequestDto;
import dev.backend.hw.domain.member.entity.Member;
import dev.backend.hw.dto.response.MemberJoinResponseDto;
import dev.backend.hw.dto.response.MemberLoginResponseDto;
import dev.backend.hw.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberLoginResponseDto login(MemberLoginRequestDto memberLoginRequestDto) {
        // 1. ID(email)/PW 로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberLoginRequestDto.getEmail(), memberLoginRequestDto.getPassword());

        // 2. 사용자 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Object principal = authentication.getPrincipal();
        log.info("principal {} ", principal);

        // 3.JWT 토큰 생성
//        String customToken = jwtTokenProvider.createToken();

        // 4. RefreshToken 저장
//        userService.createRefreshToken(authentication, customToken.getRefreshToken());
        return null;
    }

    public Member getMember(long memberId) {
        return authRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("해당 회원의 정보가 없습니다."));
    }

    public MemberJoinResponseDto join(MemberJoinRequestDto memberJoinDto) {
        return null;
    }
}
