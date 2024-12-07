package com.jfsd.jobportal.repository;

import com.jfsd.jobportal.models.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter,Integer> {
    Recruiter findByMobile(String mobile);
    Recruiter findByEmail(String email);
}
