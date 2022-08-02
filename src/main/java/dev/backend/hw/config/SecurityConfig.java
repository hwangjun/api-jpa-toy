package dev.backend.hw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.backend.hw.core.security.handler.JwtAccessDeniedHandler;
import dev.backend.hw.core.security.entrypoint.JwtAuthenticationEntryPoint;
import dev.backend.hw.core.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private final String[] ACCESS_ALLOWED_URL = new String[]{
            "/api/v1/member/**",
            "/h2-console/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()

                // 세션을 사용하지 않도록 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 데이터 확인을 위해 사용하고 있는 h2-console을 위한 설정을 추가
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 예외처리를 위한 설정
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()) // 401 status
                .accessDeniedHandler(new JwtAccessDeniedHandler()) // 403 status

                // 토근없이 사용가능한 URL 설정
                .and()
                .authorizeRequests()
                .antMatchers(ACCESS_ALLOWED_URL).permitAll()
//                .antMatchers("/api/v1/member/members").hasRole("ADMIN")
//                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
//                .and();
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico"); // swagger
        web.ignoring().antMatchers("/v3/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }


}
