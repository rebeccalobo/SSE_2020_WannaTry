package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentModuleController {
    @RequestMapping(value = "/StudentModule")
    public String goToModulePage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        return "StudentModule";
    }
}
