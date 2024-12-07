package com.jfsd.jobportal.repository;

import com.jfsd.jobportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByMobile(String mobile);
    User findByEmail(String email);
    User findById(int id);
}
