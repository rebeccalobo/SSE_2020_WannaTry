package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.*;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class DashboardController {

    @Autowired
    BackendRepoService backendRepoService;



    @GetMapping("/Dashboard")
    public String goToDashboard(HttpSession session,Model model){

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        session.setMaxInactiveInterval(120);

        //check what role they are -> if staff goto staff dashboard page

        model.addAttribute("current_user", user);


        for(Role r : user.getRoles()){
            if(r.getName().equals("ROLE_STAFF")){
                //GET LIST OF STUDENTS ENROLLED IN MODULE
                ArrayList<Modules> modules = backendRepoService.getModuleRepo().getStaffModules(user.getID());
                model.addAttribute("modules",modules);
                HashMap<String, ArrayList<Long>> hashMap = new HashMap<>();
                for(Modules m : modules){
                    hashMap.put(m.getModule_id(),
                            backendRepoService.getModuleRepo().getStudentsInSpecificModuleStaff(m.getModule_id(),user.getID()));
                }
                model.addAttribute("hashmap",hashMap);
                return "staffDashboard";
            }
        }
        return "studentDashboard";

    }


}
