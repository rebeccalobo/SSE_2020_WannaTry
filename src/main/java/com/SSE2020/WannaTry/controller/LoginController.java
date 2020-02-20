package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Students;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    BackendRepoService repoService;
    //Reference to the currently signed in user (if they exist)
    Students current_user = CurrentUserSingleton.getInstance().getCurrentUser();


    @RequestMapping(value = "/Login")
    public String showLoginPage(Model model){
        Students student = new Students();
        model.addAttribute("user",student);
        return "Login";
    }
    // Login
    @RequestMapping(value = "/login_user",method = RequestMethod.POST)
    public String getStudentById(@ModelAttribute("user")Students student, ModelMap model) throws StudentNotFoundException {
        CurrentUserSingleton.getInstance().setCurrentUser(student);
        String studentId = student.getStudent_id();
        current_user = repoService.getStudentRepo().findById(studentId)
                .orElseThrow(()->new StudentNotFoundException(studentId));
        if(current_user.getPassword().equals(student.getPassword())) {
            model.addAttribute("current_user",current_user);
            CurrentUserSingleton.getInstance().setCurrentUser(current_user);
            return "studentDashboard";
        }
        else{
            model.addAttribute("password_ok",false);
            return "Login";
        }
    }
}
