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
import com.cmpt276_gp.gp.models.Admin;
import com.cmpt276_gp.gp.models.AdminRepository;
import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.models.InstructorRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class AdminController {
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    public InstructorRepository instRepo;

    //controller for admin

    @GetMapping("/admin/exams")
    public String viewExamRequests(Model model) {
        List<Instructor> teacherTable = instRepo.findAll();
        model.addAttribute("teacherTable", teacherTable);
        return "users/admin/instructorRequests";
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
