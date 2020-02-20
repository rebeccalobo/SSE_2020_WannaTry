package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.databaserepo.StudentRepository;
import com.SSE2020.WannaTry.model.Students;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    BackendRepoService repoService;
    Students current_user = CurrentUserSingleton.getInstance().getCurrentUser();
    @GetMapping(value="/Register")
    public String register(Model model){
        Students student = new Students();
        model.addAttribute("student",student);
        model.addAttribute("incorrectFormat","correct");
        return "Register";
    }

    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student")Students student,Model model){
        String fName = student.getStudent_firstname();
        String lName = student.getStudent_surname();
        String email = student.getEmail();
        String address = student.getAddress();
        String number = student.getPhone_number();
        String pwd = student.getPassword();

        if((fName.matches("[A-Za-z]"))&&
                (lName.matches("[A-Za-z]"))&&
                (email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))&&
                (!address.isEmpty())&&
                (number.matches("[0-9]"))&&
                (pwd.matches("[A-Za-z]"))
        ){
            repoService.getStudentRepo().save(student);
            current_user = student;
            CurrentUserSingleton.getInstance().setCurrentUser(student);
            model.addAttribute("current_user",student);
            return "studentDashboard";
        }
        else{
            //model.addAttribute("flag",true);
            repoService.getStudentRepo().save(student);
            current_user = student;
            CurrentUserSingleton.getInstance().setCurrentUser(student);
            model.addAttribute("current_user",student);
            return "studentDashboard";
        }


    }
}
