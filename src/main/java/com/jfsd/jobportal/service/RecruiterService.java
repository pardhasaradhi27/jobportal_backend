package com.jfsd.jobportal.service;

import com.jfsd.jobportal.models.Recruiter;
import java.util.List;
import java.util.Optional;

public interface RecruiterService {
    Recruiter saveRecruiter(Recruiter recruiter);
    Recruiter getRecruiter(String mobile);
    List<Recruiter> getRecruiters();
    String checkRecruiter(String email, String password);
    Optional<Recruiter> getRecruiterById(int id);
}
