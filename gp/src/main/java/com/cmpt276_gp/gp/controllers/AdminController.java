package com.cmpt276_gp.gp.controllers;

import java.time.LocalDateTime;
import java.util.Map;
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

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class AdminController {
    @Autowired
    private AdminRepository adminRepo;

    //controller for admin

    // get requests
    @GetMapping("/admin/exams")
    public String viewExamRequests() {
        return "users/admin/instructorRequests";
    }
    @PostMapping("/admin/create")
    public String createRequest(@RequestParam Map<String, String> admin) {

        String courseName = admin.get("courseName");
        int duration = Integer.parseInt(admin.get("duration"));
        int section = Integer.parseInt(admin.get("section"));
        int noOfRooms = Integer.parseInt(admin.get("noOfRooms"));
        int noProctors = Integer.parseInt(admin.get("noProctors"));
        LocalDateTime startTime = LocalDateTime.parse(admin.get("startTime"));
        int roomsAssigned = Integer.parseInt(admin.get("roomsAssigned"));


        // create the instructor exam request
        Admin newRequest = new Admin(courseName, startTime, section, duration, noOfRooms,  roomsAssigned, noProctors);
        adminRepo.save(newRequest);
        return "redirect:/dashboard/admin";
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
