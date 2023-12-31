package com.cmpt276_gp.gp.controllers;

import java.time.LocalDateTime;
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
import com.cmpt276_gp.gp.models.Proctor;
import com.cmpt276_gp.gp.models.ProctorRepository;
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
    @Autowired
    private ProctorRepository procRepo;
    public Users current_user;
    //controller for admin

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model){
        return "redirect:/users/admin";
    }
    @GetMapping("/admin/proctors")
    public String showProctors(Model model){
        List<Proctor> proctors = procRepo.findAll();
        model.addAttribute("proctors", proctors);
        return "users/admin/proctors";
    }
    @GetMapping("/admin/exams")
    public String viewExamRequests(Model model) {
        List<Instructor> teacherTable = instRepo.findAll();
        model.addAttribute("teacherTable", teacherTable);
        return "users/admin/instructorRequests";
    }

    @PostMapping("/admin/create")
    public String createAdminRequest(@RequestParam Map<String, String> request, HttpServletResponse response) {
    
        // Retrieve the form data
        String courseName = request.get("courseName");
        int duration = Integer.parseInt(request.get("duration"));
        int section = Integer.parseInt(request.get("section"));
        int noProctors = Integer.parseInt(request.get("noProctors"));
        int noOfRooms = Integer.parseInt(request.get("noOfRooms"));
        int roomsAssigned = Integer.parseInt(request.get("roomsAssigned"));
        LocalDateTime startTime = LocalDateTime.parse(request.get("startTime"));
    
        Admin newRequest = new Admin(courseName, startTime, section, duration, noOfRooms, roomsAssigned, noProctors);
        adminRepo.save(newRequest);
        response.setStatus(201);
    
        return "redirect:/users/admin";
    }

     // delete a user from table
    @GetMapping("/admin/removeUser/{uid}")
    public String removeUser(@PathVariable Integer uid) {
        userRepo.deleteById(uid);
        return "redirect:/admin/users";
    }

}
