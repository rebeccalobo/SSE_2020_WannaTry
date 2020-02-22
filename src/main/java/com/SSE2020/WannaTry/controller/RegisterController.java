package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.Students;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import com.SSE2020.WannaTry.service.ValidateRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    BackendRepoService repoService;
    @Autowired
    ValidateRegistrationService validateRegistrationService;

    private Students current_user = CurrentUserSingleton.getInstance().getCurrentUser();
    @GetMapping(value="/Register")
    public String register(Model model){
        Students student = new Students();
        model.addAttribute("student",student);
        model.addAttribute("incorrectFormat","correct");
        return "Register";
    }

    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student")Students student,Model model){
        if(validateRegistrationService.validateFields(student)){
            repoService.getStudentRepo().save(student);
            current_user = student;
            CurrentUserSingleton.getInstance().setCurrentUser(student);
            model.addAttribute("current_user",student);
            return "studentDashboard";
        }
        else{
            model.addAttribute("flag",true);
            return "Register";
        }


    }
}
