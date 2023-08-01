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
    public String createRequest(@RequestParam Map<String, String> instructor){

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
        return "redirect:/dashboard/teacher";
    }

    @GetMapping("/dashboard/teacher")
    public String showIntructorPage(Model model) {
        List<Instructor> requests = instRepo.findAll();
        model.addAttribute("requests", requests);

        return "redirect:/dashboard/teacher";
    }

    // edit request attributes
    @GetMapping("/request/{uid}")
	public String editRequestForm(@PathVariable Integer uid, Model model) {
		model.addAttribute("teacher", instRepo.findById(uid).get());
		return "users/teacher/editRequest";
	}

    // save updated request information
    @PostMapping("/request/{uid}")
	public String updateRequest(@PathVariable Integer uid, @ModelAttribute("teacher") Instructor teacher) {
	
		Instructor newRequest = instRepo.findById(uid).get();
        newRequest.setCourse_name(teacher.getCourse_name());
        newRequest.setDuration(teacher.getDuration());
        newRequest.setSection(teacher.getSection());
        newRequest.setFirst_choice(teacher.getFirst_choice());
        newRequest.setSecond_choice(teacher.getSecond_choice());
        newRequest.setThird_choice(teacher.getThird_choice());
		
		instRepo.save(newRequest);
		return "redirect:/users/teacher";		
	}

    @GetMapping("/request/delete/{uid}")
    public String deleteRequest(@PathVariable Integer uid) {
        instRepo.deleteById(uid);
        return "redirect:/users/teacher";
    }

}
