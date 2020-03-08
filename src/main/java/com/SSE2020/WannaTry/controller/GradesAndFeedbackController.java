package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.StudentModuleGrades;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.ArrayList;


@Controller
public class GradesAndFeedbackController {
    @Autowired
    BackendRepoService backendRepoService;

    @RequestMapping(value = "/StudentGradesAndFeedbackPage")
    public String goToGradesPage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ArrayList<StudentModuleGrades> grades = backendRepoService.getSmgRepo().getGrades(CurrentUserSingleton.getInstance().getCurrentUser().getStudent_id(), date);
        model.addAttribute("grades",grades);
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        return "StudentGradesAndFeedbackPage";
    }
}
