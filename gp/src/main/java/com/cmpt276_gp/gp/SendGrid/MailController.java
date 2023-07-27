package com.cmpt276_gp.gp.SendGrid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sendgrid.Response;


@Controller
public class MailController {
    @Autowired
    private MailService mailSer;


    @PostMapping(value = "/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailReq ){

        Response response = mailSer.sendEmail(emailReq);
        if(response.getStatusCode() == 200 || response.getStatusCode() == 202){
            return new ResponseEntity<>("Send successfully",HttpStatus.OK);
        } return new ResponseEntity<>("Send failed", HttpStatus.NOT_FOUND);
    
    }
}
