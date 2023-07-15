package com.cmpt276_gp.gp.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.models.InstructorRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class InstructorController {
    @Autowired
    private InstructorRepository instRepo;

    // Controller for instructors

    // create request
    @PostMapping("")
    public String createRequest(@ModelAttribute Instructor instructor) {
        // create the instructor exam request
        instRepo.save(instructor);
        return "redirect:/instructor";
    }
    /*
    @PostMapping(value = "")
     * create requests
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
