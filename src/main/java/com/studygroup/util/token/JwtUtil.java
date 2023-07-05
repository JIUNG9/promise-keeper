package com.studygroup.util.token;

import com.studygroup.enums.Role;
import com.studygroup.util.constant.LoginExpirationTime;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUtil {

  public JwtUtil(){

  }
  private static final String SIGNING_KEY = "secret";
  private static final Logger LOGGER = LoggerFactory
      .getLogger(JwtUtil.class);


  public static String generateToken(String subject, List<SimpleGrantedAuthority> authorities) {

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    JwtBuilder builder = Jwts.builder()
        .setClaims(JwtClaimsUtil.simpleGrantedListToMap(authorities))
        .setSubject(subject)
        .setIssuedAt(now)
        .setExpiration(
            new Date(System.currentTimeMillis() +
                LoginExpirationTime.LOGIN_EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, SIGNING_KEY);

    String token = builder.compact();

    return token;
  }

  public static boolean
  validateToken(String token) {
    Jws<Claims> claimsJws = Jwts.parser().
        setSigningKey(SIGNING_KEY).
        parseClaimsJws(token);

    return true;

  }

  public static boolean checkTokenIsExpired(String token) {
    Jws<Claims> claimsJws = Jwts.parser().
        setSigningKey(SIGNING_KEY).
        parseClaimsJws(token);

    Claims claims = claimsJws.getBody();

    Date expiration = claims.getExpiration();

    return !expiration.after(new Date());
  }

  public static Authentication getAuthenticationFromToken(String token) {
    Claims claims =
        Jwts.parser().
            setSigningKey(SIGNING_KEY).
            parseClaimsJws(token).
            getBody();

    return new UsernamePasswordAuthenticationToken(claims.get("sub"), null,
        JwtClaimsUtil.claimsMapToList(claims));
  }


  public static String refreshToken(String token) {

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();

    return Jwts.builder().
        setClaims(claims).
        setIssuedAt(now).
        setExpiration(
            new Date(nowMillis +
                LoginExpirationTime.LOGIN_EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
  }


  public static void invalidateRelatedTokens(String token) {

    Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();

    Jwts.builder().
        setClaims(claims).
        setExpiration(
            new Date(System.currentTimeMillis()))
        .signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();


  }

  public static class JwtClaimsUtil {

    private JwtClaimsUtil(){

    }

    public static List<SimpleGrantedAuthority> claimsMapToList(Map<String, Object> claims) {

      Map<String, Object> authoritiesMap = new HashMap<>(claims);

      List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
      for (int i = 0; i < Role.values().length; i++) {
        Role role = Role.values()[i];
        if (authoritiesMap.containsKey(role.name())) {
          authorityList.add(new SimpleGrantedAuthority((String) authoritiesMap.get(role.name())));
        }
      }
      return authorityList;
    }

    public static Map<String, Object> simpleGrantedListToMap(
        List<SimpleGrantedAuthority> authorities) {
      Map<String, Object> authoritiesMap = new HashMap<>();

      for (SimpleGrantedAuthority authority : authorities) {
        authoritiesMap.put(authority.getAuthority(), authority.getAuthority());
      }

      return authoritiesMap;
    }
  }
}
