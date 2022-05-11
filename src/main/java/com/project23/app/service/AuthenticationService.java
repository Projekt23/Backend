package com.project23.app.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project23.app.Entity.User;
import com.project23.app.helper.DateHelper;
import com.project23.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.auth0.jwt.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JavaMailSender javaMailSender;
    private final DateHelper dateHelper;

    private final UserRepository userRepository;


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
            helper.setText(htmlMsg, true);
            helper.setTo(to);
            helper.setSubject("Project23 Registration");
            helper.setFrom("noreply.project22@gmail.com");
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String generateRegisterToken(String mail) {
        String token = null;
        try {
            Algorithm signatureAlgorithm = Algorithm.HMAC256("project23");
            token = JWT.create()
                    .withClaim("email", mail)
                    .withExpiresAt(dateHelper.createExpriationDate(1,0,0,0))
                    .sign(signatureAlgorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }
    public String validateRegisterToken(String token){
        String email=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("project23"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String JWTemail = jwt.getClaim("email").asString();
            if(JWTemail!=null){
                email = JWTemail;
            }
        } catch (JWTVerificationException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Invalid");
        }
        return email;
    }

    public User validateUserToken(String token){
        User user = new User();
        try {
            Algorithm algorithm = Algorithm.HMAC256("project23"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String JWTemail = jwt.getClaim("email").asString();
            String JWTpassword = jwt.getClaim("password").asString();
            String JWTusername = jwt.getClaim("userName").asString();
            String JWTname = jwt.getClaim("name").asString();
            Long JWTid = jwt.getClaim("id").asLong();
            if(JWTemail!=null && JWTpassword!=null && JWTusername!=null && JWTname!=null && JWTid!=null){
                user.setId(JWTid);
                user.setPassword(JWTpassword);
                user.setEmail(JWTemail);
                user.setName(JWTname);
                user.setUsername(JWTusername);
            }
        } catch (JWTVerificationException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Invalid");
        }
        return user;
    }




    private String generateUserToken(User u) {
        String token = null;
        try {
            Algorithm signatureAlgorithm = Algorithm.HMAC256("project23");
            token = JWT.create()
                    .withClaim("email", u.getEmail())
                    .withClaim("password", u.getPassword())
                    .withClaim("userName", u.getUsername())
                    .withClaim("name", u.getName())
                    .withClaim("id", u.getId())
                    .withExpiresAt(dateHelper.createExpriationDate(30,0,0,0))
                    .sign(signatureAlgorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    public String authenticateWithUserName(String username, String password){
        User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        if(user.getPassword().equals(password)){
            return generateUserToken(user);
        }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong Password");
    }

    public String authenticateWithEmail(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        if(user.getPassword().equals(password)){
            return generateUserToken(user);
        }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong Password");
    }



}
