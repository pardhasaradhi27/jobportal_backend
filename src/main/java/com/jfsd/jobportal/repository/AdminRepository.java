package com.jfsd.jobportal.repository;

import com.jfsd.jobportal.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsernameAndPassword(String username, String password);
    Admin findByUsername(String username);
}
