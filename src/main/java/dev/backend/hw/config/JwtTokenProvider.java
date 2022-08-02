package dev.backend.hw.config;

import dev.backend.hw.config.security.dto.MemberPrincipal;
import dev.backend.hw.domain.auth.service.AuthService;
import dev.backend.hw.domain.auth.service.TokenService;
import dev.backend.hw.domain.member.entity.Member;
import dev.backend.hw.domain.member.service.MemberService;
import dev.backend.hw.exception.AuthenticationKeyNotFoundException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final MemberService memberService;
    private final TokenService tokenService;

    private static final String SECRET_KEY = "idushj";
    private static final String AUTHORITIES_KEY = "authkey";
    private static final String BEARER_TYPE = "bearer";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String UID = "uid";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public String createToken(String email, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new AuthenticationKeyNotFoundException("인증키가 없습니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        Member member = memberService.getMember(Long.parseLong(String.valueOf(claims.get(UID))));

        UserDetails principal = new MemberPrincipal(member);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            String userPk = claims.getBody().getSubject();
            if (claims.getBody().getExpiration().before(new Date())) {
                log.debug("expired token (expiration date) {} {}", claims.getBody().getExpiration(), new Date());
                return false;
            }
            if (!tokenService.validateToken(userPk, token)) {
                log.debug("signed out token");
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String getToken(HttpServletRequest request) {
        final String startText = "Bearer ";
        String header = request.getHeader("Authorization");
        log.debug("header : {}", header);

        if (header != null && header.startsWith(startText)) {
            return header.replace(startText, "");
        }
        return null;
    }
}
