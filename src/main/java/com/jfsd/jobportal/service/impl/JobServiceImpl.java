package com.jfsd.jobportal.service.impl;

import com.jfsd.jobportal.models.Job;
import com.jfsd.jobportal.repository.JobRepository;
import com.jfsd.jobportal.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJob(int id) {
        return jobRepository.findByid(id);
    }

    @Override
    public List<Job> getJobsByRec(int id) {
        return jobRepository.findByRecruiter_Id(id);
    }


}
