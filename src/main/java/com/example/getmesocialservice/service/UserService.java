package com.example.getmesocialservice.service;

import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public User getById(String userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> getByAddress(String address) {
        return userRepository.findAllByAddress(address);
    }
//    public User getUser(){
//        return userRepository.getUser();
//    }
//    public User getUserById(int userId) {
//        return userRepository.getUserById(userId);
//    }
//    public List<User> getById(String userId) {
//        return userRepository.findAllById(userId);
//    }
//


}
