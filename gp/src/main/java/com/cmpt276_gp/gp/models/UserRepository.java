package com.cmpt276_gp.gp.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer>{
    Users findByUsername(String username);
    Users findById(int id);
    Users findByUsernameAndPassword(String username, String password);
    List<Users> findByUserType(String userType);
}
