package com.project23.app.service;

import com.project23.app.helper.DateHelper;
import com.project23.app.pojo.RegistrationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JavaMailSender javaMailSender;
    private final DateHelper dateHelper;

    private void sendRegistrationMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.project22@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public RegistrationToken createToken(String mail){
        String exp = dateHelper.createExpriationDate(24);
        return new RegistrationToken(mail, exp);
    }


}
