package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StaffNotFoundException;
import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.model.Staff;
import com.SSE2020.WannaTry.model.Students;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class LoginController {
    @Autowired
    BackendRepoService repoService;



    @RequestMapping(value = "/Login")
    public String showLoginPage(Model model){
        Students student = new Students();
        model.addAttribute("user",student);
        return "Login";
    }
    @RequestMapping(value = "/StaffLogin")
    public String showStaffLoginPage(Model model){
        Staff staff = new Staff();
        model.addAttribute("staff",staff);
        return "StaffLogin";
    }
    // Login
    @RequestMapping(value = "/login_user",method = RequestMethod.POST)
    public String getStudentById(@ModelAttribute("user")Students student, ModelMap model) throws StudentNotFoundException {

        Students queried_user;
        String studentId = student.getStudent_id();
        try{
            queried_user = repoService.getStudentRepo().findById(studentId).orElseThrow(()->new StudentNotFoundException(studentId));
        }catch (StudentNotFoundException s){
            model.addAttribute("error","No student with the id :"+studentId+" found!");
            return "Login";
        }
        if(queried_user.getPassword().equals(student.getPassword())) {
            model.addAttribute("current_user",queried_user);
            ArrayList<Modules> modules = repoService.getModuleRepo().getModules(queried_user.getStudent_id());
            model.addAttribute("modules", modules);
            CurrentUserSingleton.getInstance().setCurrentUser(queried_user);
            return "studentDashboard";
        }
        else{
            model.addAttribute("password_ok",false);
            return "Login";
        }
    }
    @RequestMapping(value = "/login_staff",method = RequestMethod.POST)
    public String getStaffById(@ModelAttribute("staff")Staff staff, ModelMap model){

        Staff queried_staff;
        String staffId = staff.getStaff_id();
        try{
            queried_staff = repoService.getStaffRepo().findById(staffId).orElseThrow(()->new StaffNotFoundException(staffId));
        }catch (StaffNotFoundException s){
            model.addAttribute("error","No staff with the id :"+staffId+" found!");
            return "StaffLogin";
        }
        if(queried_staff.getPassword().equals(staff.getPassword())) {
            model.addAttribute("current_staff",queried_staff);
            CurrentStaffSingleton.getInstance().setCurrentUser(queried_staff);
            return "staffDashboard";
        }
        else{
            model.addAttribute("password_ok",false);
            return "StaffLogin";
        }
    }
}

