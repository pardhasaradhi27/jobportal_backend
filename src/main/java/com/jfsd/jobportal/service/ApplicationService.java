package com.jfsd.jobportal.service;

import com.jfsd.jobportal.models.Application;
import com.jfsd.jobportal.models.Job;

import java.util.List;

public interface ApplicationService {
    Application saveApplication(Application application);
    Application getApplication(String mobile);
    List<Application> getApplications();
    List<Application> getApplicationsByRecruiterId(int recruiterId);
    List<Application> getApplicationsByEmail(String userEmail);
}
