package com.project23.app.service;

import com.project23.app.Entity.User;
import com.project23.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Fügt einen neuen User hinzu
     * @param u User
     * @return User ID
     */
    public long addUser(User u){
        if(!userExists(u)){
            return userRepository.save(u).getId();
        }else throw new ResponseStatusException(HttpStatus.CONFLICT, "Entity already exists" );
    }

    /**
     * Überprüft ob Nutzername oder Email bereits vergeben sind
     * @param u User
     * @return True oder False
     */
    private boolean userExists(User u){
        if(userRepository.findByEmail(u.getEmail()).isPresent()||userRepository.findByUsername(u.getUsername()).isPresent()){
            return true;
        }
        return false;
    }

    /**
     * Returned alle User
     * @return Liste von allen Usern
     */
    public List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }

    /**
     * Returned ein einzelner User anhand der ID
     * @param id User ID
     * @return User
     */
    public  User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }

    /**
     * Löscht User anhand der ID
     * @param id User ID
     */
    public void deleteUser(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        userRepository.deleteById(id);
    }

    /**
     * Updated die Userinformationen. Username und Email sollten hier nicht bereits vergeben sein
     * @param u User
     */
    public void updateUser(User u) {
        User user = userRepository.findById(u.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

        if(!u.getEmail().equals(user.getEmail())){
            if(userRepository.findByEmail(u.getEmail()).isEmpty()) {
                user.setEmail(u.getEmail());
            }else throw new ResponseStatusException(HttpStatus.CONFLICT, "E-Mail already used" );
        }

        if(!u.getUsername().equals(user.getUsername())) {
            if(userRepository.findByUsername(u.getUsername()).isEmpty()) {
                user.setUsername(u.getUsername());
            }else throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used" );
        }
        user.setLastName(u.getLastName());
        user.setFirstName(u.getFirstName());
        user.setPassword(u.getPassword());
        userRepository.save(user);
    }

    /*public User getUser(Long userId) {
        return userRepository.getById(userId);
    }*/
}
