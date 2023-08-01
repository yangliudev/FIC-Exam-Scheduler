package com.cmpt276_gp.gp.SendGrid;

import java.io.IOException;
import java.util.HashMap; // Import for Map and HashMap classes
import java.util.Map;    // Import for Map and HashMap classes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sendgrid.Response;

@RestController
public class MailController {
    @Autowired
    private MailService mailSer;

    @PostMapping(value = "/sendEmail")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailRequest emailReq) {
        Response response = mailSer.sendEmail(emailReq);
        if (response.getStatusCode() == 200 || response.getStatusCode() == 202) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Email sent successfully");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Failed to send email");
            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
