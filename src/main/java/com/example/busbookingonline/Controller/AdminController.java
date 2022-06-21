package com.example.busbookingonline.Controller;

import com.example.busbookingonline.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    ModelAndView mv = new ModelAndView();

    @GetMapping("/adminRegPage")
    public ModelAndView registrationPage() {
        LOGGER.info("AdminController/Registerpage()");
        mv.setViewName("admin/registerAdmin");
        return mv;
    }

    @PostMapping("/adminReg")
    public ModelAndView saveAdmin(AdminDto adminDto) {
        LOGGER.info("AdminController/Register" + adminDto);
        if (adminService.saveAdmin(adminDto) != null) {
            mv.addObject("Admin", adminDto.getEmail()).setStatus(HttpStatus.ACCEPTED);
            mv.setViewName("admin/registerAdmin");
            String successMessage = "Admin Added Successfully";
            mv.getModelMap().addAttribute("success", successMessage);
        } else {
            mv.setViewName("admin/registerAdmin");
        }
        return mv;
    }

    @GetMapping("/adminLoginPage")
    public ModelAndView loginPage() {
        LOGGER.info("AdminController/showlogin () ");
        mv.setStatus(HttpStatus.FOUND);
        mv.setViewName("admin/login");
        return mv;
    }

    @PostMapping("/adminLogin")
    public ModelAndView login(String email, String password, HttpSession session) {
        LOGGER.info("AdminController/login");
        LOGGER.info("JSESSIONID : " + session.getId());
        Admin saveAdmin = adminService.saveAdmin(email, password);
        if (saveAdmin != null) {
            mv.addObject("savedadmin", saveAdmin).setViewName("admin/Home");
        } else {
            String message = "Could Not Reach/Find Your Account , Try Again ";
            mv.addObject("message", message);
            mv.setViewName("admin/login");
        }
        return mv;
    }

    @GetMapping("/admins")
    public ModelAndView displayAllAdmins() {
        LOGGER.info("AdminController/displayAdmins");
        List<Admin> admins = adminService.getAllAdmins();
        mv.addObject("admins", admins).setStatus(HttpStatus.FOUND);
        mv.setViewName("admin/Admins");
        return mv;
    }

    @GetMapping("/updateAdmin")
    public ModelAndView updatePage(@RequestParam("id") Long id) {
        Admin admin = adminService.getAdminById(id);
        mv.addObject("admin", admin).setViewName("admin/UpdateAdmin");
        return mv;
    }

    @PostMapping("/adminUpdate")
    public ModelAndView updateAdmin(@ModelAttribute("admin") Admin admin) {
        LOGGER.info("AdminController/updateAdmin");
        adminService.updateAdmin(admin);
        List<Admin> admins = adminService.getAllAdmins();
        mv.addObject("admins", admins).setViewName("admin/Admins");
        mv.setStatus(HttpStatus.OK);
        return mv;

    }

    @GetMapping("adminDelete")
    @Transactional
    public ModelAndView deleteAdminById(@RequestParam("id") Long id) {
        adminService.deleteAdminById(id);
        mv.setViewName("admin/Admins");
        List<Admin> admins = adminService.getAllAdmins();
        mv.addObject("admins", admins);
        return mv;
    }

    @GetMapping("adminHome")
    public ModelAndView adminHomepage() {
        mv.setViewName("admin/Home");
        return mv;
    }

}