package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.ModuleNotFoundException;
import com.SSE2020.WannaTry.exceptions.StaffNotFoundException;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.model.Staff;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
