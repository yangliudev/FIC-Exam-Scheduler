package com.cmpt276_gp.gp.controllers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.models.InstructorRepository;
import com.cmpt276_gp.gp.models.Proctor;
import com.cmpt276_gp.gp.models.ProctorRepository;
import com.cmpt276_gp.gp.models.UserRepository;
import com.cmpt276_gp.gp.models.Users;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class ProctorControlller {
    @Autowired
    private ProctorRepository procRepo;
    @Autowired
    public InstructorRepository instRepo;
    @Autowired
    public UserRepository userRepo;
    @GetMapping("/proctor/dashboard")
    public String showDashboard(Model model){
        List<Proctor> requests = procRepo.findAll();
        model.addAttribute("requests", requests);
        return "users/proctor/proctor";
    }
    @PostMapping("/proctor/non-available-dates")
    public String nonAvailDates(@RequestParam("proctorUser") String username, @RequestParam("role") String role, @RequestParam("nonAvailableDates") List<String> nonAvailableDates, Model model){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDate> nonAvailableDateTimes = nonAvailableDates.stream()
                .map(dateString -> LocalDate.parse(dateString, formatter))
                .collect(Collectors.toList());
        Proctor newProc = new Proctor(username, nonAvailableDateTimes, role);
        procRepo.save(newProc);
        Users currentUser = userRepo.findByUsername(username);
        model.addAttribute("user", currentUser);
        List<Proctor> requests = procRepo.findAll();
        model.addAttribute("requests", requests);

        return "users/proctor/proctor";
    }
    @GetMapping("/proctor/pending-exams") 
    public String viewExamRequests(Model model) {
        List<Instructor> teacherTable = instRepo.findAll();
        model.addAttribute("teacherTable", teacherTable);
        return "users/proctor/pendingExams";
    }
    @GetMapping("/proctor/my-non-available-dates")
    public String myNonAvailDates(Model model){
        return "users/proctor/proctorNADates";
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
