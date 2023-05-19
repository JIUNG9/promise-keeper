package com.studygroup.securirty.filter;


import com.studygroup.exception.ApiError;
import com.studygroup.util.CookieUtil;
import com.studygroup.util.JwtUtil;
import com.studygroup.util.constant.ConvertObjectToJson;
import com.studygroup.util.constant.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@AllArgsConstructor

public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtils;

    private static final String jwtTokenCookieName = "jwtToken";
    private static final String signingKey = "secret";
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticationException {

        String token = CookieUtil.getValue(request,"jwtToken");

        HttpStatus status = null;
        ErrorCode errorCode = null;

        try {
            if (token != null && JwtUtil.validateToken(token) && !JwtUtil.checkTokenIsExpired(token)) {
                Authentication authentication = JwtUtil.getAuthenticationFromToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                status = HttpStatus.UNAUTHORIZED;
                errorCode = ErrorCode.TOKEN_IS_NOT_VALID;
                response.
                        getWriter().
                        write(ConvertObjectToJson.convert(
                                ApiError.
                                        buildApiError(errorCode, status).getBody()));
                return;
            }
        } catch (ExpiredJwtException e) {
            status = HttpStatus.UNAUTHORIZED;
            errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
            response.
                    getWriter().
                    write(ConvertObjectToJson.convert(
                            ApiError.
                                    buildApiError(errorCode, status).getBody()));
            return;
        }

        filterChain.doFilter(request, response);


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/api/users") || path.equals("/api/login") || path.contains("/api/auth/");
    }
}

