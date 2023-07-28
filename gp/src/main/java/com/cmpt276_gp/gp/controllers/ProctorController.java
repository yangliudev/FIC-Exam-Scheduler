package com.cmpt276_gp.gp.controllers;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.cmpt276_gp.gp.models.Proctor;
import com.cmpt276_gp.gp.models.ProctorRepository;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProctorController {

    @Autowired
    private ProctorRepository procRepo;

    @Autowired
    private InstructorRepository instRepo;

    @GetMapping("/proctor/pending-exams")
    public String viewExamRequests(Model model) {
        List<Instructor> teacherTable = instRepo.findAll();
        model.addAttribute("teacherTable", teacherTable);
        return "users/proctor/pendingExams";
    }

    @PostMapping("/proctor/non-available-dates")
    public String nonAvailableDates(@RequestParam("nonAvailableDates") List<String> nonAvailableDateStrings,
            Model model) {
        List<LocalDateTime> parsedDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
        for (String dateString : nonAvailableDateStrings) {
            LocalDate date = LocalDate.parse(dateString, formatter);
            LocalDateTime dateTime = date.atStartOfDay();
            parsedDates.add(dateTime);
        }
    
        Proctor proctor = new Proctor(parsedDates);
        procRepo.save(proctor);
    
        // Add the success message to the model
        model.addAttribute("successMessage", "Non-available dates saved successfully!");
    
        // Reload the view without redirecting
        return viewExamRequests(model);
    }

    // create request
    /*
    @PostMapping(value = "")
    * create requests
    */

    // edits
    /*
    @PostMapping(value ="")
    * edit requests
    */

}
