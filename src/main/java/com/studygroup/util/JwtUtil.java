package com.studygroup.util;

import com.studygroup.enums.Role;
import com.studygroup.util.constant.LoginExpirationTime;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class JwtUtil {
    private static final String signingKey = "secret";
    private static final Logger logger = LoggerFactory
            .getLogger(JwtUtil.class);


    public  static String generateToken(String subject, List<SimpleGrantedAuthority> authorities) {


        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);



        JwtBuilder builder = Jwts.builder()
                .setClaims(JwtClaimsUtil.SimpleGrantedListToMap(authorities))
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(
                        new Date(System.currentTimeMillis() +
                                LoginExpirationTime.LOGIN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, signingKey);

        String token = builder.compact();


        return token;
    }

    public static boolean validateToken(String token) {

            Jws<Claims> claimsJws = Jwts.parser().
                    setSigningKey(signingKey).
                    parseClaimsJws(token);

            return true;

    }

    public static boolean checkTokenIsExpired(String token) {
        Jws<Claims> claimsJws = Jwts.parser().
                setSigningKey(signingKey).
                parseClaimsJws(token);

        Claims claims = claimsJws.getBody();

        Date expiration = claims.getExpiration();

        return !expiration.after(new Date());
    }

        public static Authentication getAuthenticationFromToken(String token) {
        Claims claims =
                Jwts.parser().
                        setSigningKey(signingKey).
                        parseClaimsJws(token).
                        getBody();

        return new UsernamePasswordAuthenticationToken(claims.get("sub"), null, JwtClaimsUtil.ClaimsMapToList(claims));
    }


    public static String refreshToken(String token) {

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();

            return Jwts.builder().
                    setClaims(claims).
                    setIssuedAt(now).
                    setExpiration(
                            new Date(nowMillis +
                                    LoginExpirationTime.LOGIN_EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, signingKey).compact();
        }




    public static void invalidateRelatedTokens(String token)
    {

        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();

             Jwts.builder().
                setClaims(claims).
                setExpiration(
                        new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, signingKey).compact();


    }

}
