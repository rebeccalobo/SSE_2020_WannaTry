package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.ModuleNotFoundException;
import com.SSE2020.WannaTry.exceptions.StaffNotFoundException;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
public class StaffController {

    @Autowired
    BackendRepoService repoService;

    @RequestMapping(value ="/edit_description", method = RequestMethod.POST)
    public String editDescription(@RequestParam("description") String desc,@RequestParam("module_id")String module_id ) throws StaffNotFoundException, ModuleNotFoundException {
        Modules modules = repoService.getModuleRepo().findById(module_id).orElseThrow(()->new ModuleNotFoundException(module_id));
        modules.setDescription(desc);
        repoService.getModuleRepo().save(modules);
        return "redirect:/StaffDashboard";
    }

}
