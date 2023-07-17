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
    private String course_name;
    private int duration;
    private int section;
    private LocalDateTime first_choice; // format (yyyy-mm-ddThh:mm:ss:ns)
    private LocalDateTime second_choice; // format (yyyy-mm-ddThh:mm:ss:ns)
    private LocalDateTime third_choice; // format (yyyy-mm-ddThh:mm:ss:ns)

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
    public Instructor(String course_name, int duration, int section, LocalDateTime first_choice, 
                    LocalDateTime second_choice, LocalDateTime third_choice){
        this.course_name = course_name;
        this.duration = duration;
        this.section = section;
        this.first_choice = first_choice;
        this.second_choice = second_choice;
        this.third_choice = third_choice;
    }

    // Getters
    public String getCourse_name() {
        return course_name;
    }
    
    public int getDuration(){
        return duration;
    }

    public int getSection(){
        return section;
    }

    public LocalDateTime getFirst(){
        return first_choice;
    }

    public LocalDateTime getSecond(){
        return second_choice;
    }

    public LocalDateTime getThird(){
        return third_choice;
    }
        

    // Setters
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setSection(int section){
        this.section = section;
    }

    public void setFirst(LocalDateTime first_choice){
        this.first_choice = first_choice;
    }

    public void setSecond(LocalDateTime second_choice){
        this.second_choice = second_choice;
    }

    public void setThird(LocalDateTime third_choice){
        this.third_choice = third_choice;
    }
}
