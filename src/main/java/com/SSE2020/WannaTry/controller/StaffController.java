package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.ModuleNotFoundException;
import com.SSE2020.WannaTry.exceptions.StaffNotFoundException;
import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.model.LoggedActions;
import com.SSE2020.WannaTry.databaserepo.LoggedActionsRepository;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.util.Date;

@Controller
@ControllerAdvice
public class StaffController {

    @Autowired
    BackendRepoService repoService;

    final LoggedActionsRepository loggedActionsRepository;

    public StaffController(LoggedActionsRepository loggedActionsRepository) {
        this.loggedActionsRepository = loggedActionsRepository;
    }

    @Transactional
    @RequestMapping(value ="/edit_description", method = RequestMethod.POST)
    public String editDescription(@RequestParam("description") String desc,@RequestParam("module_id")String module_id, HttpServletRequest request) throws StaffNotFoundException, ModuleNotFoundException {
        Modules modules = repoService.getModuleRepo().findById(module_id).orElseThrow(()->new ModuleNotFoundException(module_id));

        CustomUserDetails details = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = details.getUser();
        if(modules.getLecturer_id() == user.getID()){
            modules.setDescription(desc);
            repoService.getModuleRepo().save(modules);

            String ip = request.getRemoteAddr();
            loggedActionsRepository.log(user.getID(), "MODULE EDIT", "User: " + user.getFName() + " " + user.getLName() + " edited the details of the module:  " + modules.getModule_name() + ".", ip, new Date());

            return "redirect:/Dashboard";
        }else{
            return "redirect:/logout";
        }

    }

}
