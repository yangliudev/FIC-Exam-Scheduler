package com.cmpt276_gp.gp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime; // used for time
import java.time.temporal.ChronoUnit; // for turnicating the time for better formatting


@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private int duration;
    private int section;
    private LocalDateTime firstChoice; // format (yyyy-mm-ddThh:mm:ss:ns)
    private LocalDateTime secondChoice; // format (yyyy-mm-ddThh:mm:ss:ns)
    private LocalDateTime thirdChoice; // format (yyyy-mm-ddThh:mm:ss:ns)

    /* 
    if you want to make get just the get just yyyy-mm-dd
    Refer to the following code
    
     class datetimesample {
         public static void main(String[] args) {
             LocalDateTime time = LocalDateTime.now();
             System.out.println(time);
             System.out.println(time.truncatedTo(ChronoUnit.SECONDS)); output-> in format (yyyy-mm-ddThh:mm)
            }
    */


    // Default constructor
    public Instructor(){

    }

    // Parameters constructor
    public Instructor(int duration, int section, LocalDateTime firstChoice, 
                    LocalDateTime secondChoice, LocalDateTime thirdChoice){
        this.duration = duration;
        this.section = section;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
    }

    // Getters
    public int getDuration(int duration){
        return duration;
    }

    public int getSection(int section){
        return section;
    }

    public LocalDateTime getFirst(LocalDateTime firstChoice){
        return firstChoice;
    }

    public LocalDateTime getSecond(LocalDateTime secondChoice){
        return secondChoice;
    }

    public LocalDateTime getThird(LocalDateTime thirdChoice){
        return thirdChoice;
    }
        

    // Setters
    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setSection(int section){
        this.section = section;
    }

    public void setFirst(LocalDateTime firstChoice){
        this.firstChoice = firstChoice;
    }

    public void setSecond(LocalDateTime secondChoice){
        this.secondChoice = secondChoice;
    }

    public void setThird(LocalDateTime thirdChoice){
        this.thirdChoice = thirdChoice;
    }
}
