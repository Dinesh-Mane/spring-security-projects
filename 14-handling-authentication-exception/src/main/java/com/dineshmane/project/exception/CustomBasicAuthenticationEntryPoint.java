package com.dineshmane.project.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("error-reason", "Authentication Failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.setContentType("application/json;charset=UTF-8");

        LocalDateTime timestamp = LocalDateTime.now();
        String msg = (authException != null && authException.getMessage()!=null)? authException.getMessage():"Unauthorized";
        String path = request.getRequestURI();
        String jsonResponse = String.format(
                "{\n" +
                "    \"timestamp\": \"%s\",\n" +
                "    \"status\": %d,\n" +
                "    \"error\": \"%s\",\n" +
                "    \"message\": \"%s\",\n" +
                "    \"path\": \"%s\"\n" +
                "}"
                , timestamp, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),msg, path);

        response.getWriter().write(jsonResponse);

    }
}
