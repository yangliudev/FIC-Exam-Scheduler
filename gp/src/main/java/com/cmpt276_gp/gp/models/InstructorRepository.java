package com.cmpt276_gp.gp.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer>{
    Instructor findBySection(int section);
    Instructor findById(int uid);
    //Instructor findByUser(String User?);
}
