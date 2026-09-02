package com.dineshmane.project.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setHeader("denied-reason", "Authorization Failed");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        response.setContentType("application/json;charset=UTF-8");

        LocalDateTime timestamp = LocalDateTime.now();
        String msg = (accessDeniedException != null && accessDeniedException.getMessage()!=null)? accessDeniedException.getMessage():"Authorization failed";
        String path = request.getRequestURI();
        String jsonResponse = String.format(
                "{\n" +
                        "    \"timestamp\": \"%s\",\n" +
                        "    \"status\": %d,\n" +
                        "    \"error\": \"%s\",\n" +
                        "    \"message\": \"%s\",\n" +
                        "    \"path\": \"%s\"\n" +
                        "}"
                , timestamp, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),msg, path);

        response.getWriter().write(jsonResponse);


    }
}
