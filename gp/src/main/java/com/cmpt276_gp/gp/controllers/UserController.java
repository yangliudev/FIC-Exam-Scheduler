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
    public String LoginUser(@RequestParam Map<String, String> loginUser, HttpServletResponse response){
        String username = loginUser.get("username");
        String password = loginUser.get("password");
        Users user = userRepo.findByUsernameAndPassword(username, password);
        if(user != null){
            String type = user.getUserType();
            if(type == "admin"){
                // landing page for admins
                return "users/admin/admin";
            } else if(type == "teacher"){
                return "users/teacher/teacher";
            } else{
                return "users/proctor/proctor";
            }
        }
        // back to login page with error 
        return "something for now";
    }
}