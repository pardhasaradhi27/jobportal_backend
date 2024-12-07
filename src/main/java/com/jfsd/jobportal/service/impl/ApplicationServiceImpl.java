package com.jfsd.jobportal.service.impl;

import com.jfsd.jobportal.models.Application;
import com.jfsd.jobportal.models.Job;
import com.jfsd.jobportal.repository.ApplicationRepository;
import com.jfsd.jobportal.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application getApplication(String mobile) {
        return applicationRepository.findByMobile(mobile);
    }

    @Override
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }
    @Override
    public List<Application> getApplicationsByRecruiterId(int recruiterId) {
        return applicationRepository.findByJob_Recruiter_Id(recruiterId);
    }

    @Override
    public List<Application> getApplicationsByEmail(String userEmail) {
        return applicationRepository.findByEmail(userEmail);
    }



}
