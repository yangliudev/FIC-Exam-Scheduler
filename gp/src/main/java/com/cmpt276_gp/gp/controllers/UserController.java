package com.cmpt276_gp.gp.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cmpt276_gp.gp.models.Users;
import com.cmpt276_gp.gp.models.UserRepository;
import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.models.InstructorRepository;
import com.cmpt276_gp.gp.models.Admin;
import com.cmpt276_gp.gp.models.AdminRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserController {
    @Autowired
    public UserRepository userRepo;
    @Autowired
    public InstructorRepository instRepo;
    @Autowired
    public AdminRepository adminRepo;
    
    public Users current_user;

    @PostMapping("/users/login")
    public String loginUser(@RequestParam Map<String, String> loginUser, HttpServletResponse response, Model model) {
        String username = loginUser.get("username");
        String password = loginUser.get("password");
        Users user = userRepo.findByUsernameAndPassword(username, password);
        current_user = user;

        if (user != null && user.getPassword().equals(password)) {
            String type = user.getUserType();
            if ("admin".equals(type)) {
                // landing page for admins
                return "redirect:/users/admin";
            } else if ("teacher".equals(type)) {
                return "redirect:/users/teacher";
            } else {
                return "redirect:/users/proctor";
            }
        } 
       else {
        model.addAttribute("error", "Invalid username or password.");
        return "users/login";
    }
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

    @PostMapping("/users/userpage")
    public String updateUser(@ModelAttribute("user") Users user, @RequestParam("password") String password, 
                             @RequestParam("currentPassword") String currentPassword,
                             @RequestParam("newPassword") String newPassword,
                             @RequestParam("confirmPassword") String confirmPassword,
                             Model model) {
        // Check if the current password matches the password in the database
        if (currentPassword.equals(current_user.getPassword())) {
            // Check if the new password and confirm password match
            if (newPassword.equals(confirmPassword)) {
                // Update the user's password
                current_user.setPassword(newPassword);
                userRepo.save(current_user);
                // Redirect to the corresponding page based on the user type
                String userType = current_user.getUserType();
                if ("teacher".equals(userType)) {
                    return "redirect:/users/teacher";
                } else if ("admin".equals(userType)) {
                    return "redirect:/users/admin";
                } else {
                    return "redirect:/users/proctor";
                }
            } else {
                // Password and confirm password do not match
                model.addAttribute("error", "New password and confirm password do not match.");
            }
        } else {
            // Current password is incorrect
            model.addAttribute("error", "Incorrect current password.");
        }
        // If there is an error, return to the edit page
        return "users/edit";
    }

    @GetMapping("/users/edit")
	public String editUserForm(Model model) {
		model.addAttribute("user", current_user);
		return "users/edit";
	}

    @GetMapping("/users/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("user", current_user);
        List<Admin> adminTable = adminRepo.findAll();
        model.addAttribute("adminTable", adminTable);
        return "users/admin/admin";
    }

    @GetMapping("/admin/users")
    public String getAllUsers(Model model){ 
        model.addAttribute("currentUser", current_user);
        List<Users> allUsers = userRepo.findAll(); 
        model.addAttribute("users", allUsers);
        return "users/admin/adminUsers";
    }

    @GetMapping("/users/teacher")
    public String showTeacherPage(Model model) {
        model.addAttribute("user", current_user);
        List<Instructor> teacherTable = instRepo.findAll();
        model.addAttribute("teacherTable", teacherTable);
        return "users/teacher/teacher";
    }

    @GetMapping("/users/proctor")
    public String showProctorPage(Model model) {
        model.addAttribute("user", current_user);
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