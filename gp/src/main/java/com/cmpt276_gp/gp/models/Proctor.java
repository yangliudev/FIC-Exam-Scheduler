package com.cmpt276_gp.gp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "proctor")
public class Proctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @ElementCollection
    private List<LocalDateTime> nonAvailableDates;

    // Default constructor
    public Proctor() {

    }

    // Parameters Constructor
    public Proctor(List<LocalDateTime> nonAvailableDates) {
        this.nonAvailableDates = nonAvailableDates;
    }

    // Getters
    public List<LocalDateTime> getNonAvailableDates() {
        return nonAvailableDates;
    }

    // Setters
    public void setNonAvailableDates(List<LocalDateTime> nonAvailableDates) {
        this.nonAvailableDates = nonAvailableDates;
    }
}
