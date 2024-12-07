package com.jfsd.jobportal.service.impl;

import com.jfsd.jobportal.models.Recruiter;
import com.jfsd.jobportal.repository.RecruiterRepository;
import com.jfsd.jobportal.service.RecruiterService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private RecruiterRepository recruiterRepository;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public Recruiter saveRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @Override
    public Recruiter getRecruiter(String mobile) {
        return recruiterRepository.findByMobile(mobile);
    }
    @Override
    public List<Recruiter> getRecruiters(){
        return recruiterRepository.findAll(Sort.by("id"));
    }

    @Override
    public String checkRecruiter(String email, String password) {
        Recruiter recruiter = recruiterRepository.findByEmail(email);
        System.out.println("Fetched Recruiter: " + recruiter); // Check what user is fetched
        if (recruiter != null) {
            System.out.println("User password from DB: " + recruiter.getPassword()); // Check stored password
        }
        if (recruiter != null && recruiter.getPassword().equals(password)) {
            return recruiter.getMobile();
        }
        return null; // Invalid credentials
    }

    @Override
    public Optional<Recruiter> getRecruiterById(int id) {
        return recruiterRepository.findById(id);
    }
}
