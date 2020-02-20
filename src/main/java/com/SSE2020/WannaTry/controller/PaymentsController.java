package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentsController {
    @RequestMapping(value = "/Payments")
    public String goToPaymentPage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        return "Payments";
    }
}
