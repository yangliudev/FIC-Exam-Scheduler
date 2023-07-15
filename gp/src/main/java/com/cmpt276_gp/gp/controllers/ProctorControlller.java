package com.cmpt276_gp.gp.controllers;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cmpt276_gp.gp.models.Proctor;
import com.cmpt276_gp.gp.models.ProctorRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class ProctorControlller {
    @Autowired
    private ProctorRepository procRepo;

    // Controller for Proctors

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
