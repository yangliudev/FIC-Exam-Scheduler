package com.cmpt276_gp.gp.SendGrid;

public class EmailRequest {
    private String to;
    private String subject;
    private String body;

    // Getters
    public String getTo(){
        return to;
    }

    public String getSubject(){
        return subject;
    }

    public String getBody(){
        return body;
    }

    // Setters

    public void setTo(String to){
        this.to = to;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setBody(String body){
        this.body = body;
    }

    // Request Constructor
    public EmailRequest(String to, String subject, String body){
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
