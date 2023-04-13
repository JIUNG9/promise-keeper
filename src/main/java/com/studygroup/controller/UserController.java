package com.studygroup.controller;

import com.studygroup.service.EmailServiceImpl;
import com.studygroup.service.UserService;
import com.studygroup.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
}
