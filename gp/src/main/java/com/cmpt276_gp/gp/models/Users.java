package com.cmpt276_gp.gp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String username;
    private String password;
    private String userType;
    private String email;

    // Default constructor
    public Users(){

    }

    // Parameters constructor
    public Users(String username, String password, String userType, String email){
        this.username = username;
        this.password = password; 
        this.userType = userType;
        this.email = email;
    }

    // Getters
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getUserType(){
        return userType;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUsertype(String userType){
        this.userType = userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

