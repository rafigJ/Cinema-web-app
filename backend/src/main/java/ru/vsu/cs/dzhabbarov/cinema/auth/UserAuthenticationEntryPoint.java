package ru.vsu.cs.dzhabbarov.cinema.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.vsu.cs.dzhabbarov.cinema.config.RestExceptionResponse;

import java.io.IOException;


@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        if (request.getAttribute("error") != null) {
            OBJECT_MAPPER.writeValue(response.getOutputStream(), new RestExceptionResponse(request.getAttribute("error").toString()));
        } else {
            OBJECT_MAPPER.writeValue(response.getOutputStream(), new RestExceptionResponse("Unauthorized path"));
        }
    }
}
