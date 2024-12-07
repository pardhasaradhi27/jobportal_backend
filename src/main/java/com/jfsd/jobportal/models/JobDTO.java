package com.jfsd.jobportal.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


public class JobDTO {

    private int id;

    private String name;

    private String company;

    private String mobile;

    private double salary;

    private String location;
    private String shift;
    private String description;
    private Recruiter recruiter;

    public JobDTO(int id, String name, String company, double salary, String location,String shift, String description, Recruiter recruiter) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.location = location;
        this.description = description;
        this.recruiter = recruiter;
        this.shift=shift;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
