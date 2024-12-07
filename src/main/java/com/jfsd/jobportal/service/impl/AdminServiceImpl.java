package com.jfsd.jobportal.service.impl;

import com.jfsd.jobportal.models.Admin;
import com.jfsd.jobportal.models.User;
import com.jfsd.jobportal.repository.AdminRepository;
import com.jfsd.jobportal.repository.UserRepository;
import com.jfsd.jobportal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    @Override
    public List<User> viewUsers() {
        return userRepository.findAll();
    }

    @Override
    public String logoutAdmin() {
        return "";
    }

    @Override
    public Admin checkAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }


}
