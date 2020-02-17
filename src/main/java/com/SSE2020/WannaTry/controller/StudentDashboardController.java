package com.SSE2020.WannaTry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentDashboardController {

    @RequestMapping(value = "/studentDashboard")
    public String goToDashboardPage(){
        return "studentDashboard";
    }
    @RequestMapping(value = "/StudentModule")
    public String goToModulePage(){
        return "StudentModule";
    }
    @RequestMapping(value = "/Payments")
    public String goToPaymentPage(){
        return "Payments";
    }
    @RequestMapping(value = "/StudentGradesAndFeedbackPage")
    public String goToGradesPage(){
        return "StudentGradesAndFeedbackPage";
    }
}
