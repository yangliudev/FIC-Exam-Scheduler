package com.cmpt276_gp.gp.SendGrid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class MailConfig {
    @Value("${sendgrid.key}")
    private String key;
    
    @Bean
    public SendGrid getSendGrid(){
        return new SendGrid(key);
    }
    
}
