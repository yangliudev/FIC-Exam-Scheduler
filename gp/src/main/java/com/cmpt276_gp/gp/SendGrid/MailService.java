package com.cmpt276_gp.gp.SendGrid;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public Response sendEmail(EmailRequest emailReq){
        // for testing 
        Mail mail = new Mail(new Email(/*email*/), emailReq.getSubject(), new Email(emailReq.getTo()), new Content("text/plain", emailReq.getBody()));
        mail.setReplyTo(new Email(/*email*/));

        Request request = new Request();

        Response response = null;

        try{ 
            request.setMethod(Method.POST);
            
            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            response = this.sendGrid.api(request);

        }

        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return response;
    }
}
