package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.databaserepo.StudentRepository;
import com.SSE2020.WannaTry.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @GetMapping(value="/Register")
    public String register(Model model){
        Students student = new Students();
        model.addAttribute("student",student);
        model.addAttribute("incorrectFormat","correct");
        return "Register";
    }
}
