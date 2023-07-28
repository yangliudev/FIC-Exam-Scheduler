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
    // Add roles: room invigilator and hall monitor
    private String proctorUser;

    private List<LocalDateTime> nonAvailableDates;

    // Default constructor
    public Proctor() {

    }

    // Parameters Constructor
    public Proctor(String proctorUser, List<LocalDateTime> nonAvailableDates) {
        this.proctorUser = proctorUser;
        this.nonAvailableDates = nonAvailableDates;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProctorUser() {
        return proctorUser;
    }

    public void setProctorUser(String proctorUser) {
        this.proctorUser = proctorUser;
    }

    public List<LocalDateTime> getNonAvailableDates() {
        return nonAvailableDates;
    }

    public void setNonAvailableDates(List<LocalDateTime> nonAvailableDates) {
        this.nonAvailableDates = nonAvailableDates;
    }


}
