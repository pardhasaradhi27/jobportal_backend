package com.jfsd.jobportal.service;

import com.jfsd.jobportal.models.Job;
import com.jfsd.jobportal.repository.JobRepository;

import java.util.List;

public interface JobService {
    Job saveJob(Job job);
    List<Job> getJobs();
    Job getJob(int id);
    List<Job> getJobsByRec(int id);
}
