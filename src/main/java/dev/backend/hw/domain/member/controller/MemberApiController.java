package dev.backend.hw.domain.member.controller;


import dev.backend.hw.common.BasicResponse;
import dev.backend.hw.domain.member.service.MemberService;
import dev.backend.hw.dto.response.MemberDetailResponseDto;
import dev.backend.hw.dto.response.MemberListResponseDto;
import dev.backend.hw.dto.response.MemberOrdersResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Tag(name = "member", description = "회원 API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * - 회원 가입
     * - 회원 로그인(인증)
     * - 회원 로그아웃
     * - 단일 회원 상세 정보 조회
     * - 단일 회원의 주문 목록 조회
     * - 여러 회원 목록 조회 :
     * - 페이지네이션으로 일정 단위로 조회합니다.
     * - 이름, 이메일을 이용하여 검색 기능이 필요합니다.
     * - 각 회원의 마지막 주문 정보
     */



    @Operation(summary = "단일 회원 상세정보 조회", description = "회원 ID로 상세정보 조회 가능합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "단일 회원 상세정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")
    })
    @GetMapping("/member/detail/{memberId}")
    public ResponseEntity findMemberDetail(@Parameter(description = "회원 ID") @PathVariable Long memberId,
                                           HttpServletRequest request) {
        MemberDetailResponseDto memberDetailResponseDto = memberService.findMemberDetail(memberId);

        return new ResponseEntity(BasicResponse.success(
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "단일 회원 상세정보 조회 성공",
                request.getRequestURI(),
                memberDetailResponseDto), HttpStatus.OK);
    }

    @Operation(summary = "단일 회원의 주문 목록 조회", description = "회원 ID로 주문목록 조회 가능합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "단일 회원의 주문 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = MemberOrdersResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")
    })
    @GetMapping("/member/{memberId}/orders")
    public ResponseEntity findMemberOrders(@Parameter(description = "회원 ID") @PathVariable Long memberId, HttpServletRequest request) {
        List<MemberOrdersResponseDto> memberOrders = memberService.findMemberOrders(memberId);

        return new ResponseEntity(BasicResponse.success(
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "조회 성공",
                request.getRequestURI(),
                memberOrders), HttpStatus.OK);
    }

    @Operation(summary = "여러 회원 목록 조회", description = "회원 ID로 상세정보 조회 가능합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "여러 회원 목록 조회 조회 성공",
                    content = @Content(schema = @Schema(implementation = MemberListResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")
    })
    @Parameters({
            @Parameter(description = "검색조건 이름", in = ParameterIn.QUERY),
            @Parameter(description = "검색조건 이메일", in = ParameterIn.QUERY),
            @Parameter(description = "페이지", in = ParameterIn.QUERY),
            @Parameter(description = "사이즈", in = ParameterIn.QUERY)
    })
    @GetMapping("/member/orders")
    public ResponseEntity findMemberLastOrder(
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false, defaultValue = "0") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer size,
                                              HttpServletRequest request) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<MemberListResponseDto> memberList = memberService.findMemberList(name, email, pageable);

        return new ResponseEntity(BasicResponse.success(
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "조회 성공",
                request.getRequestURI(),
                memberList), HttpStatus.OK);
    }
}
