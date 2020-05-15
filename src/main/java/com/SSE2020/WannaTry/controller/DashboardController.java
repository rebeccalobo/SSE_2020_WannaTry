package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.*;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalDouble;

@Controller
public class DashboardController {

    @Autowired
    BackendRepoService backendRepoService;


    @Transactional
    @GetMapping("/Dashboard")
    public String goToDashboard(HttpSession session,Model model){

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        session.setMaxInactiveInterval(120);

        model.addAttribute("current_user", user);

        for(Role r : user.getRoles()){
            if(r.getName().equals("ROLE_STAFF")){
                //GET LIST OF STUDENTS ENROLLED IN MODULE
                ArrayList<Modules> modules = backendRepoService.getModuleRepo().getStaffModules(user.getID());
                model.addAttribute("modules",modules);
                HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();
                for(Modules m : modules){
                    hashMap.put(m.getModule_id(),
                            backendRepoService.getModuleRepo().getStudentsInSpecificModuleStaff(m.getModule_id(),user.getID()));
                }
                model.addAttribute("hashmap",hashMap);
                model.addAttribute("isStaff",true);
                model.addAttribute("isStudent",false);
                model.addAttribute("flag",false);
                return "staffDashboard";
            }
        }
        Optional<Double> feeOptional = backendRepoService.getModuleRepo().getFeesDue(user.getID());

        if(!feeOptional.isPresent()){
            Optional<Double> fees_due =  backendRepoService.getModuleRepo().calculateFees(user.getID());
            if(fees_due.isPresent()){
                backendRepoService.getModuleRepo().updateFees(user.getID(),fees_due.get());
            }else{
                backendRepoService.getModuleRepo().updateFees(user.getID(),0.00);
            }
        }
        //check what role they are -> if staff goto staff dashboard page
        if(areFeesPaid(user.getID())){
            model.addAttribute("fees_paid",true);
        }else{
            model.addAttribute("fees_paid",false);
        }
        model.addAttribute("isStaff",false);
        model.addAttribute("isStudent",true);
        model.addAttribute("flag",false);
        return "studentDashboard";
    }

    private boolean areFeesPaid(int id){
       Optional<Double> feesDue = backendRepoService.getModuleRepo().getFeesDue(id);
       if(feesDue.isPresent()){
           double due = (double)feesDue.get();
           return due==0.00;
       }

       return false;
    }

}
