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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.auth0.jwt.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    private final JavaMailSender javaMailSender;
    private final DateHelper dateHelper;

    private final UserRepository userRepository;

    @Value("${host.frontend.baseurl}")
    private String baseUrl;

    /**
     * Erstellt eine E-Mail als String mit Url spezifischer Weiterleitung. Hierfür wird das Template unter <a href="file:../resources/mail.html">/resources/mail.html</a> verwendet.
     * @param url Verlinkung in der E-Mail
     * @return E-Mail als String
     */
    private String createRegistrationMail(String url){
        File in = new File("email.html");
        Document doc = null;
        Element a1 = null;
        try {
            InputStream is = new ClassPathResource("mail.html").getInputStream();
            copyInputStreamToFile(is, in);
            doc = Jsoup.parse(in, "UTF-8");
            Elements elements = doc.select("a");
            a1 = elements.get(0);
            a1.attr("href",url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return doc.toString();
    }

    /**
     * Hilfsmethode um eine Datei aus einem InputStream zu erstellen
     * @param inputStream InputStream
     * @param file Output File
     * @throws IOException
     */
    private void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }

    /**
     * Hilfsmethode um E-Mail zu validieren
     * @param email Zu validierende E-Mail
     * @return
     */
    public static boolean eMailIsValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Methode um Registrierungs Mail zu versenden.
     * @param to E-Mail Empfänger
     * @param token Registrierungstoken
     */
    public void sendRegistrationMail(String to, String token) {
        if(!eMailIsValid(to)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"E-Mail is not valid");
        }
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = createRegistrationMail(baseUrl+"/register#"+token);
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

    /**
     * Erstellt einen Registrierungstoken
     * @param mail  Mail, welche im Token hinterlegt wird
     * @param days  Tage bis der Token expired
     * @param hours Stunden bis der Token expired
     * @param minutes Minuten bis der Token expired
     * @param seconds Secunden bis der Token expired
     * @return Registrierungstoken
     */
    public String generateRegisterToken(String mail, int days, int hours, int minutes, int seconds) {
        String token = null;
        try {
            Algorithm signatureAlgorithm = Algorithm.HMAC256("project23");
            token = JWT.create()
                    .withClaim("email", mail)
                    .withExpiresAt(dateHelper.createExpriationDate(days,hours,minutes,seconds))
                    .sign(signatureAlgorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    /**
     * Methode um Registrierungstoken zu validieren
     * @param token Registrierungstoken
     * @return True oder False
     */
    public String validateRegisterToken(String token){
        String email=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("project23"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String JWTemail = jwt.getClaim("email").asString();
            email = JWTemail;
        } catch (JWTVerificationException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Invalid");
        }
        return email;
    }

    /**
     * Methode um Usertoken zu validieren
     * @param token Registrierungstoken
     * @return True oder False
     */
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
            String JWTfirstName = jwt.getClaim("firstName").asString();
            String JWTlastName = jwt.getClaim("lastName").asString();
            Long JWTid = jwt.getClaim("id").asLong();
            user.setId(JWTid);
            user.setPassword(JWTpassword);
            user.setEmail(JWTemail);
            user.setFirstName(JWTfirstName);
            user.setLastName(JWTlastName);
            user.setUsername(JWTusername);

        } catch (JWTVerificationException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Invalid");
        }
        return user;
    }


    /**
     * Erstellt einen Usertoken
     * @param u User welcher im Token hinterlegt wird
     * @param days  Tage bis der Token expired
     * @param hours Stunden bis der Token expired
     * @param minutes Minuten bis der Token expired
     * @param seconds Secunden bis der Token expired
     * @return Userstoken
     */
    private String generateUserToken(User u, int days, int hours, int minutes, int seconds) {
        String token = null;
        try {
            Algorithm signatureAlgorithm = Algorithm.HMAC256("project23");
            token = JWT.create()
                    .withClaim("email", u.getEmail())
                    .withClaim("userName", u.getUsername())
                    .withClaim("firstName", u.getFirstName())
                    .withClaim("lastName", u.getLastName())
                    .withClaim("id", u.getId())
                    .withExpiresAt(dateHelper.createExpriationDate(days,hours,minutes,seconds))
                    .sign(signatureAlgorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    /**
     * Methode um Nutzer mit dem Nutzername/Passwort zu authentifizieren
     * @param username Nutzername
     * @param password Passwort
     * @return Usertoken
     */
    public String authenticateWithUserName(String username, String password){
        User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        if(user.getPassword().equals(password)){
            return generateUserToken(user,30,0,0,0);
        }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong Password");
    }

    /**
     * Methode um Nutzer mit Email/Passwort zu authentifizieren
     * @param email Email
     * @param password Passwort
     * @return Usertoken
     */
    public String authenticateWithEmail(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        if(user.getPassword().equals(password)){
            return generateUserToken(user,30,0,0,0);
        }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong Password");
    }



}
