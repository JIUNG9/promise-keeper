package com.studygroup.service.user;

import com.studygroup.util.CookieUtil;
import com.studygroup.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("NormalLogoutService")
public class NormalLogoutService implements LogoutService {
    @Override
    public void logout(HttpServletRequest request ,HttpServletResponse response, String tokenName) {
        String token  = CookieUtil.getValue(request,tokenName);
        JwtUtil.invalidateRelatedTokens(token);
        CookieUtil.clear(response,tokenName);

    }
}
