package com.jfsd.jobportal.service.impl;

import com.jfsd.jobportal.models.User;
import com.jfsd.jobportal.repository.UserRepository;
import com.jfsd.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll(Sort.by("id"));
    }
    @Override
    public User checkUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        System.out.println("Fetched User: " + user); // Check what user is fetched
        if (user != null) {
            System.out.println("User password from DB: " + user.getPassword()); // Check stored password
        }
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Invalid credentials
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }


}
