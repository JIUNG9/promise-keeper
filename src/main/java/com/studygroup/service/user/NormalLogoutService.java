package com.studygroup.service.user;

import com.studygroup.util.cookie.CookieUtil;
import com.studygroup.util.token.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service("NormalLogoutService")
public class NormalLogoutService implements LogoutService {

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, String tokenName) {
    String token = CookieUtil.getValue(request, tokenName);
    JwtUtil.invalidateRelatedTokens(token);
    CookieUtil.clear(response, tokenName);

  }
}
