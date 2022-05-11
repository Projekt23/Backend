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

    public long addUser(User u){
        if(!userExists(u)){
            return userRepository.save(u).getId();
        }else throw new ResponseStatusException(HttpStatus.CONFLICT, "Entity already exists" );
    }
    private boolean userExists(User u){
        if(userRepository.findByEmail(u.getEmail()).isPresent()||userRepository.findByUsername(u.getUsername()).isPresent()){
            return true;
        }
        return false;
    }
    public List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }
    public  User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }

    public void deleteUser(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        userRepository.deleteById(id);
    }
    public void updateUser(User u) {
        User user = userRepository.findById(u.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        user.setEmail(u.getEmail());
        user.setName(u.getName());
        user.setPassword(u.getPassword());
        user.setUsername(u.getUsername());
        if(!userExists(user)){
            userRepository.save(user);
        }else throw new ResponseStatusException(HttpStatus.CONFLICT, "Entity already exists" );
    }


}
