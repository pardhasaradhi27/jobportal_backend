package com.jfsd.jobportal.service;

import com.jfsd.jobportal.models.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(String mobile);
    List<User> getUsers();
    User checkUser(String email, String password);
    User getUserById(int id);
}
