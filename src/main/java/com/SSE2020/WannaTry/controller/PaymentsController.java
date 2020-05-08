//package com.SSE2020.WannaTry.controller;
//
//
//import com.SSE2020.WannaTry.model.Students;
//import com.SSE2020.WannaTry.model.User;
//import com.SSE2020.WannaTry.service.BackendRepoService;
//import com.SSE2020.WannaTry.service.CurrentUserSingleton;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
//@Controller
//public class PaymentsController {
//
//    @Autowired
//    BackendRepoService repoService;
//    private User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//    @RequestMapping(value = "/Payments")
//    public String goToPaymentPage(Model model){
//
//        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
//        model.addAttribute("fees_input", "");
//        try{
//            double fees = repoService.getModuleRepo().getFees(user.getID());
//            model.addAttribute("fees", fees);
//        }catch (NullPointerException e){
//            model.addAttribute("fees", 0.0);
//        }
//
//
//        return "Payments";
//    }
//
//    @RequestMapping(value="/update_balance",method= RequestMethod.POST)
//    public String updateBalance(@ModelAttribute("fees_input")String input, Model model){
//
//        Double value = Double.valueOf(input);
//        students.setAmount_paid(students.getAmount_paid() + value);
//        repoService.getStudentRepo().save(students);
//        CurrentUserSingleton.getInstance().setCurrentUser(students);
//        model.addAttribute("current_user", students);
//        double fees = repoService.getModuleRepo().getFees(user.getID());
//        model.addAttribute("fees", fees);
//        return "Payments";
//
//
//    }
//}
