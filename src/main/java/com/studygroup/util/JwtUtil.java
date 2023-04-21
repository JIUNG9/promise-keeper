package com.studygroup.util;

import com.studygroup.entity.Member;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class JwtUtil {
    private String signingKey ="secret";
    private static final Logger logger = LoggerFactory
            .getLogger(JwtUtil.class);


    public String generateToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        String token = builder.compact();


        return token;
    }

    //check the time also
    public boolean validateToken(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey, String token) throws RuntimeException {

        if (token == null) {
            logger.info("There is no token");
            //this error will be caught by custom exception filter.
            throw new AuthenticationCredentialsNotFoundException("JWT token is null");
        }
        //2. if there is cookie check the cookie with subject name
        String subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        String memberId =((Member)SecurityContextHolder.
                                                        getContext().
                                                        getAuthentication().
                                                        getPrincipal()).
                                                        getId().
                                                        toString();
        if(!subject.equals(memberId)){
            return false;
        }
        return true;
    }

//    public boolean invalidateRelatedTokens(HttpServletRequest httpServletRequest,
//                                           String jwtTokenCookieName)
//    {
//
//
//
//
//    }
}