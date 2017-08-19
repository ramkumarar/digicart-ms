package com.wilson.retail.service;

import com.wilson.retail.entity.User;
import com.wilson.retail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List)userRepository.findAll();
    }

    public List<User> findByLastName(String name) {
        return userRepository.findByLastName(name);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

}
