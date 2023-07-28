package com.cmpt276_gp.gp.models;
import java.time.LocalDate;
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
    private List<LocalDate> nonAvailableDates;
    private String username;
    private String role;
    // Default constructor
    public Proctor() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Parameters Constructor
    public Proctor(String username, List<LocalDate> nonAvailableDates, String role) {
        this.username = username;
        this.nonAvailableDates = nonAvailableDates;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getters
    public List<LocalDate> getNonAvailableDates() {
        return nonAvailableDates;
    }

    // Setters
    public void setNonAvailableDates(List<LocalDate> nonAvailableDates) {
        this.nonAvailableDates = nonAvailableDates;
    }
}
