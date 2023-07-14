package com.cmpt276_gp.gp.models;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ProctorRepository  extends JpaRepository<Proctor,Integer>{

    Proctor findByNonAvailDates(LocalDateTime nonAvailableDates);
}
