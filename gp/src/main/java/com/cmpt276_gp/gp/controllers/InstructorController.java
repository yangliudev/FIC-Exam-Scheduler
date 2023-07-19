package com.cmpt276_gp.gp.controllers;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.models.InstructorRepository;
import com.cmpt276_gp.gp.models.Users;
import com.cmpt276_gp.gp.models.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class InstructorController {
    @Autowired
    public InstructorRepository instRepo;

    @Autowired
    public UserRepository userRepo;

    public Users current_user;
    // Controller for instructors

    // create request
    @PostMapping("/instructor/create")
    public String createRequest(@RequestParam Map<String, String> instructor) {

        String course_name = instructor.get("course_name");
        int duration = Integer.parseInt(instructor.get("duration"));
        int section = Integer.parseInt(instructor.get("section"));
        LocalDateTime firstChoice = LocalDateTime.parse(instructor.get("firstChoice"));
        LocalDateTime secondChoice = LocalDateTime.parse(instructor.get("secondChoice"));
        LocalDateTime thirdChoice = LocalDateTime.parse(instructor.get("thirdChoice"));
        String instructorUser = instructor.get("instructorUser"); 
        current_user = userRepo.findByUsername(instructorUser);


        // create the instructor exam request
        Instructor newRequest = new Instructor(course_name, duration, section, firstChoice, secondChoice, thirdChoice, instructorUser);
        instRepo.save(newRequest);

        // still need to fix routing since teacher.html cannot read user model
        return "redirect:/dashboard/teacher";
    }

    @GetMapping("/dashboard/teacher")
    public String showIntructorPage(Model model) {
        model.addAttribute("user", current_user);
        List<Instructor> teacherTable = instRepo.findAll();
        model.addAttribute("teacherTable", teacherTable);
        return "users/teacher/teacher";
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
