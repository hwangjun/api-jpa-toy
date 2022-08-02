package dev.backend.hw.domain.auth.controller;

import dev.backend.hw.common.BasicResponse;
import dev.backend.hw.config.JwtTokenProvider;
import dev.backend.hw.domain.auth.service.AuthService;
import dev.backend.hw.domain.auth.service.TokenService;
import dev.backend.hw.domain.member.dto.MemberJoinRequestDto;
import dev.backend.hw.domain.member.dto.MemberLoginRequestDto;
import dev.backend.hw.dto.response.MemberJoinResponseDto;
import dev.backend.hw.dto.response.MemberLoginResponseDto;
import dev.backend.hw.dto.response.MemberOrdersResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Tag(name = "auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthApiController {

    private final AuthService authService;
    private final TokenService tokenService;
    private final JwtTokenProvider jwtTokenProvider;


    @Operation(summary = "회원가입", description = "회원가입", responses = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = MemberOrdersResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")
    })
    @PostMapping("/auth/join")
    public ResponseEntity join(@RequestBody @Valid MemberJoinRequestDto memberJoinDto, HttpServletRequest request) {
        MemberJoinResponseDto newMember = authService.join(memberJoinDto);

        return new ResponseEntity(BasicResponse.success(
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "회원 가입 성공",
                request.getRequestURI(),
                newMember), HttpStatus.OK);
    }

    @Operation(summary = "로그인", description = "이메일, 비밀번호 를 통해 로그인이 가능합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = MemberLoginResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")
    })
    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginRequestDto memberLoginRequestDto,
                                HttpServletRequest request) {
        MemberLoginResponseDto memberLoginResponseDto = authService.login(memberLoginRequestDto);

        return new ResponseEntity(BasicResponse.success(
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "로그인 성공",
                request.getRequestURI(),
                memberLoginResponseDto), HttpStatus.OK);
    }

    @Operation(summary = "로그아웃", description = "이메일, 비밀번호 를 통해 로그아웃 가능합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")
    })
    @GetMapping("/auth/logout")
    public String logout() {
        return "로그아웃";
    }

}
