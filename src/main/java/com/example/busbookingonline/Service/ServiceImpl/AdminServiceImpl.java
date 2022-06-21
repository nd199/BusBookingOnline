package com.example.busbookingonline.Service.ServiceImpl;

import com.example.busbookingonline.Entity.Admin;
import com.example.busbookingonline.Entity.AdminRepository;
import com.example.busbookingonline.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private  AdminRepository adminRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin addAdmin(String name, Long Id) {
        return null;
    }
}
