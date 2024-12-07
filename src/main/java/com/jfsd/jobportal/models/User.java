package com.jfsd.jobportal.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="mobile")
    private String mobile;
    @Column(name="fname")
    private String firstName;
    @Column(name="lname")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="age")
    @Min(value = 18, message = "Age must be atleast 18")
    private int age;
    @Column(name="password")
    private String password;
    @Column(name="linkedin_link")
    private String linkedinLink;

    @Column(name="student")
    private String student;
    @Column(name="college")
    private String college;

//    @Column(name="photo")
//    private String photoUrl;


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Min(value = 18, message = "Age must be atleast 18")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 18, message = "Age must be atleast 18") int age) {
        this.age = age;
    }
}
