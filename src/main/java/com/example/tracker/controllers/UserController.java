package com.example.tracker.controllers;

import com.example.tracker.models.User;
import com.example.tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("message", "User registered successfully!");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
        // Assuming you have a method in UserService to authenticate users
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if (isAuthenticated) {
            // Redirect to a dashboard page or any other page after successful login
            return "redirect:/courses";
        } else {
            // If authentication fails, return to the login page with an error message
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }

    }


    // Additional methods for handling login can be added here
}
