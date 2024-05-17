package com.example.tracker.services;

import com.example.tracker.models.User;
import com.example.tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticateUser(String email, String password) {
        // Retrieve the user from the database based on the email
        User user = userRepository.findByEmail(email);

        // Check if any user is found with the provided email
        if (user != null && user.getPassword().equals(password)) {
            // Authentication successful
            return true;
        } else {
            // Authentication failed
            return false;
        }
    }



}
