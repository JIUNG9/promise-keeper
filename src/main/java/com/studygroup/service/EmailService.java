package com.studygroup.service;

import com.studygroup.entity.EmailToken;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Properties;

 interface EmailService {

    Authenticator setAuth();

    void sendEmail(Session session, String toEmail, String subject, String body);

    EmailToken  tokenSave();

    EmailToken findByConfirmationToken(String confirmationToken);

    Properties setSMTPSession();

    String setTokenString();

}

