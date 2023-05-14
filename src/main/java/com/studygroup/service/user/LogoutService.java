package com.studygroup.service.user;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface LogoutService {
    void logout(HttpServletRequest request, HttpServletResponse response, String tokenName);
}
