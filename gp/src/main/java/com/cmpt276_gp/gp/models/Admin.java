package com.cmpt276_gp.gp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime; //used for time
import java.time.temporal.ChronoUnit; //for turnicating the time for better formatting

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String courseName;
    private LocalDateTime startTime;
    private int section;
    private int duration;
    private int noOfRooms;
    private int roomsAssigned;
    private int noProctors;


    // Default constructor
    public Admin(){

    }

    // Parameters construtor
    public Admin(String courseName, LocalDateTime startTime, 
                int section, int duration, int noOfRooms, int noProctors){
        
        this.courseName = courseName;
        this.startTime = startTime;
        this.section = section;
        this.duration = duration;
        this.noOfRooms = noOfRooms;
        this.noProctors = noProctors;
    }

    // Getters
    public String getCourseName(String courseName){
        return courseName;
    }

    public LocalDateTime getStartTime(LocalDateTime startTime){
        return startTime;
    }

    public int getSection(int section){
        return section;
    }

    public int getDuration(int duration){
        return duration;
    }

    public int getNoOfRooms(int noOfRooms){
        return noOfRooms;
    }

    public int getRoomsAssigned(int roomsAssigned){
        return roomsAssigned;
    }

    public int getNoProctors(int noProctors){
        return noProctors;
    }

    // Setters
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public void setSection(int section){
        this.section = section;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setNoOfRooms(int noOfRooms){
        this.noOfRooms = noOfRooms;
    }

    public void setRoomsAssigned(int roomsAssigned){
        this.roomsAssigned = roomsAssigned;
    }

    public void setNoProctors(int noProctors){
        this.noProctors = noProctors;
    }


}




