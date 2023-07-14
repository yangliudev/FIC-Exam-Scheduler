package com.cmpt276_gp.gp.models;

import java.util.List;
import java.time.LocalDateTime;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
    Admin findBySection(int section);
    Admin findByCourseName(String courseName);
    Admin findById(int uid);
    Admin findByStartTime(LocalDateTime startTime);
}
