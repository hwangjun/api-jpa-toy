package dev.backend.hw.core.security.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.backend.hw.core.response.ApiErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType( MediaType.APPLICATION_JSON_VALUE );

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setErrorMessage(authException.getMessage());
        apiErrorResponse.setErrorCode(HttpServletResponse.SC_UNAUTHORIZED);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, String.valueOf(apiErrorResponse));

    }
}
