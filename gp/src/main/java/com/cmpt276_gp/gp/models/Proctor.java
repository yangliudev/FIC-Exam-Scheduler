package com.cmpt276_gp.gp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime; //used for time

@Entity
@Table(name = "proctor")
public class Proctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private LocalDateTime nonAvailableDates;

    // Default constructor
    public Proctor(){

    }


    // Parameters Constructor
    public Proctor(LocalDateTime nonAvailableDates){
        this.nonAvailableDates = nonAvailableDates;
    }

    // Getters
    public LocalDateTime getNonAvailableDates(LocalDateTime nonAvailableDates){
        return nonAvailableDates;
    }

    // Setters
    public void setNonAvailableDates(LocalDateTime nonAvailableDates){
        this.nonAvailableDates = nonAvailableDates;
    }
}


