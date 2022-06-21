package com.example.busbookingonline.Service;

import com.example.busbookingonline.Entity.Admin;

public interface AdminService {

    Admin addAdmin(Admin admin);

    Admin addAdmin(String name, Long Id);

}
