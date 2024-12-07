package com.jfsd.jobportal.repository;

import com.jfsd.jobportal.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Integer> {
    Job findByid(int id);
    List<Job> findByRecruiter_Id(int id);
}
