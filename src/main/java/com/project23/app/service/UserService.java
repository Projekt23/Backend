package com.project23.app.service;

import com.project23.app.pojo.User;
import com.project23.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(User u){
        userRepository.save(u);
    }

    public List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }

}
