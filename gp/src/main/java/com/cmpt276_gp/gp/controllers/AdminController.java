package com.cmpt276_gp.gp.controllers;

import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cmpt276_gp.gp.models.Admin;
import com.cmpt276_gp.gp.models.AdminRepository;
import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.models.InstructorRepository;
import com.cmpt276_gp.gp.models.UserRepository;
import com.cmpt276_gp.gp.models.Users;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class AdminController {
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    public InstructorRepository instRepo;
    @Autowired
    private UserRepository userRepo;

    //controller for admin

    @GetMapping("/admin/exams")
    public String viewExamRequests(Model model) {
        List<Instructor> teacherTable = instRepo.findAll();
        model.addAttribute("teacherTable", teacherTable);
        return "users/admin/instructorRequests";
    }
    @GetMapping("/admin/users")
    public String getAllUsers(Model model){
        List<Users> allUsers = userRepo.findAll();
        model.addAttribute("users", allUsers);
        return "users/admin/adminUsers";
    }
    @PostMapping("/admin/removeUser")
    public String removeUser(@RequestParam("username") String username, Model model){
        Users deleteUser = userRepo.findByUsername(username);
        userRepo.delete(deleteUser);
        List<Users> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "redirect:/admin/users";
    }
    @PostMapping("admin/edit")
     public String updateUser(@ModelAttribute("user") Users user, @RequestParam("password") String password, 
                             @RequestParam("currentPassword") String currentPassword,
                             @RequestParam("newPassword") String newPassword,
                             @RequestParam("confirmPassword") String confirmPassword,
                             Model model) {
        // Check if the current password matches the password in the database
        if (currentPassword.equals(user.getPassword())) {
            // Check if the new password and confirm password match
            if (newPassword.equals(confirmPassword)) {
                // Update the user's password
                user.setPassword(newPassword);
                userRepo.save(user);
                // Redirect to the corresponding page based on the user type
                return "redirect:/admin/users";
            } else {
                // Password and confirm password do not match
                model.addAttribute("error", "New password and confirm password do not match.");
            }
        } else {
            // Current password is incorrect
            model.addAttribute("error", "Incorrect current password.");
        }
        // If there is an error, return to the edit page
        return "admin/adminEdit";
    }
    @GetMapping("admin/adminEdit")
    public String editUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("email", email);
        
		return "users/admin/adminEdit";
    }
    /*
    @GetMapping(value ="")
     * get all requests
     */

    // edits
    /*
    @PostMapping(value ="")
     * edit requests
    */

    // deletes
    /*
    @PostMapping(value = "")
     *  delete requests
    */
    
    // others 
}
