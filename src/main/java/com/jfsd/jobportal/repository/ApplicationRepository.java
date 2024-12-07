package com.jfsd.jobportal.repository;

import com.jfsd.jobportal.models.Application;
import com.jfsd.jobportal.models.Job;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByMobile(String mobile);
    List<Application> findByJob_Recruiter_Id(int recruiterId);
    List<Application> findByEmail(String userEmail);


}
