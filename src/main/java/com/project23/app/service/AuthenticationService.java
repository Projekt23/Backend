package com.project23.app.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project23.app.helper.DateHelper;
import com.project23.app.pojo.RegistrationToken;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.auth0.jwt.*;
import org.springframework.util.StreamUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JavaMailSender javaMailSender;
    private final DateHelper dateHelper;

    @Value("classpath:mail.html")
    private Resource resource;

    private String createRegistrationMail(String url){
        File in = null;
        Document doc = null;
        Element a1 = null;
        try {
            in = resource.getFile();
            doc = Jsoup.parse(in, null);
            Elements elements = doc.select("a");
            a1 = elements.get(0);
            a1.attr("href",url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return doc.toString();
    }

    public void sendRegistrationMail(String to, String token) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = createRegistrationMail("http://localhost:8080/api/auth/validate?token="+token);
        try {
//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(to);
            helper.setSubject("Project23 Registration");
            helper.setFrom("noreply.project22@gmail.com");
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String createJWT(String mail) {
        String token = null;
        try {
            Algorithm signatureAlgorithm = Algorithm.HMAC256("project23");
            token = JWT.create()
                    .withClaim("email", mail)
                    .withExpiresAt(dateHelper.createExpriationDate(24))
                    .sign(signatureAlgorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }
    public String validateToken(String token){
        String email=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("project23"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String JWTemail = jwt.getClaim("email").asString();
            long jwtExpiresAt = jwt.getExpiresAt().getTime()/1000;
            long difference = jwtExpiresAt - (System.currentTimeMillis()/1000);
            if(jwt != null && JWTemail!=null && difference >= 30){
                email = JWTemail;
            }
        } catch (JWTVerificationException exception){

        }
        return email;
    }




}
