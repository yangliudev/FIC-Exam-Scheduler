package com.cmpt276_gp.gp.SendGrid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class MailService {
    @Autowired
    SendGrid sendGrid;

    public Response sendEmail(EmailRequest emailReq) {
        Mail mail = new Mail(new Email("sjd8@sfu.ca"), emailReq.getSubject(), new Email(emailReq.getTo()), new Content("text/plain", emailReq.getBody()));
        mail.setReplyTo(new Email("sjd8@sfu.ca"));
    
        Request request = new Request();
        Response response = null;
    
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
    
            response = this.sendGrid.api(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
        return response;
    }
}
