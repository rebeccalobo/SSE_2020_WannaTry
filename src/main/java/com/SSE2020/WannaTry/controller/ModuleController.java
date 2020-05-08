package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.databaserepo.ModuleEnrolmentsRepository;
import com.SSE2020.WannaTry.exceptions.EnrolementNotFoundException;
import com.SSE2020.WannaTry.exceptions.ModuleNotFoundException;
import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.ModuleEnrolment;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
public class ModuleController {
    @Autowired
    BackendRepoService repoService;

    //ENROL AND UNENROL
    @RequestMapping(value = "/enrol",method = RequestMethod.POST)
    public String enrol(@RequestParam("module")String module ){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        ModuleEnrolment me = new ModuleEnrolment(module,user.getID());
        repoService.getEnrolRepo().save(me);
        return "redirect:/StudentModule";
    }
    @RequestMapping(value = "/un_enrol",method = RequestMethod.POST)
    public String UnEnrol(Model model,@RequestParam("module")String module){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        repoService.getModuleRepo().unEnrolStudent(user.getID(),module);
        return "redirect:/StudentModule";
    }
}
