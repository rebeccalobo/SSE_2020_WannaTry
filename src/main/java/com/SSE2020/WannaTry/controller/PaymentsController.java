package com.SSE2020.WannaTry.controller;


import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.Optional;


@Controller
public class PaymentsController {

    @Autowired
    BackendRepoService repoService;


    @RequestMapping(value = "/Payments",method = RequestMethod.GET)
    public String goToPaymentPage(Model model){

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("current_user",user);

        Optional<Double> opt_fees_due = repoService.getModuleRepo().getFeesDue(user.getID());
        if(opt_fees_due.isPresent()){
            if(opt_fees_due.get() == 0.00){
                return "redirect:/Dashboard";
            }
            model.addAttribute("fees_due",opt_fees_due.get());
            return "Payments";
        }
        return "error";
    }

    @Transactional
    @RequestMapping(value="/update_balance",method= RequestMethod.POST)
    public String updateBalance(@ModelAttribute("fees_input")double input, Model model){

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Optional<Double> opt_fees_due = repoService.getModuleRepo().getFeesDue(user.getID());

        double input_amount = Double.valueOf(input);

        if(opt_fees_due.get()-input_amount <0.00){
            model.addAttribute("invalid",true);
            return "Payments";
        }
        repoService.getModuleRepo().updateFees(user.getID(),opt_fees_due.get()-input_amount);
        return "redirect:/Payments";
    }
}
