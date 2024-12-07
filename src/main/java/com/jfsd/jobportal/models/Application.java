package com.jfsd.jobportal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "mobile")
    private String mobile;
    @Column(name="email")
    private String email;
    @Column(name="fname")
    private String firstName;
    @Column(name="lname")
    private String lastName;
    @Column(name="age")
    private int age;
    @Column(name="jobname")
    private String jobName;
    @ManyToOne(fetch = FetchType.LAZY) // You can use FetchType.EAGER based on your requirement
    @JoinColumn(name = "job_id", nullable = false) // Ensure this column exists in your applications table
    private Job job;



    @Column(name="status", nullable = false) // New status column with NOT NULL constraint
    private String status;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
