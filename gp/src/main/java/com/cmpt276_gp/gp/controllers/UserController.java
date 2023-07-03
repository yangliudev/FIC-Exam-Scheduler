package com.cmpt276_gp.gp.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.cmpt276_gp.gp.models.Users;
import com.cmpt276_gp.gp.models.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class UserController {
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/users/login")
    public String loginUser(@RequestParam Map<String, String> loginUser, HttpServletResponse response) {
        String username = loginUser.get("username");
        String password = loginUser.get("password");
        Users user = userRepo.findByUsernameAndPassword(username, password);
        if (user != null) {
            String type = user.getUserType();
            if ("admin".equals(type)) {
                // landing page for admins
                return "users/admin/admin";
            } else if ("teacher".equals(type)) {
                return "users/teacher/teacher";
            } else {
                return "users/proctor/proctor";
            }
        }
        // back to login page with error 
        return "something for now";
    }
    

    @PostMapping("/users/add")
    public String addUserString(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("ADD user");
    
        // Retrieve the form data
        String username = newuser.get("username");
        String password = newuser.get("password");
        String userType = newuser.get("userType");
        String email = newuser.get("email");
    
        // Create a new Users object with the retrieved data
        Users newUser = new Users(username, password, userType, email);
        // Save the new Users object to the repository
        userRepo.save(newUser);
        response.setStatus(201);
        
        // Redirect to the login page
        return "redirect:/users/login";
    }

    @GetMapping("/users/admin")
    public String showAdminPage() {
        return "users/admin/admin";
    }

    @GetMapping("/users/teacher")
    public String showTeacherPage() {
        return "users/teacher/teacher";
    }

    @GetMapping("/users/proctor")
    public String showProctorPage() {
        return "users/proctor/proctor";
    }

    @GetMapping("/users/newUser")
    public String showNewUserPage() {
        return "users/newUser";
    }
    
    @GetMapping("/users/login")
    public String showLoginPage() {
        return "users/login";
    }

    @GetMapping("/users/logout") 
    public String logout() {
        return "users/login";
    }
    
    

}