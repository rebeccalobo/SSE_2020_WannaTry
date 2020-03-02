package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.ModuleNotFoundException;
import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Get All Notes
    @GetMapping("/module")
    public List<Modules> getAllNotes() {
        return repoService.getModuleRepo().findAll();
    }

    // Create a new Note
    @PostMapping("/module")
    public Modules createNote(@Valid @RequestBody Modules modules) {
        return repoService.getModuleRepo().save(modules);
    }
    // Update a Note
    @PutMapping("/module/{id}")
    public Modules updateNote(@PathVariable(value = "id") String moduleId,
                            @Valid @RequestBody Modules moduleDetails) throws ModuleNotFoundException {

        Modules modules = repoService.getModuleRepo().findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));



        modules.setModule_name(moduleDetails.getModule_name());
        modules.setDescription(moduleDetails.getDescription());
        modules.setLecturer_id(moduleDetails.getLecturer_id());

        return repoService.getModuleRepo().save(modules);
    }

    // Delete a Note
    @DeleteMapping("/module/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") String moduleId) throws ModuleNotFoundException {
        Modules modules = repoService.getModuleRepo().findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        repoService.getModuleRepo().delete(modules);

        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/StaffModule")
    public String goToModulePage(Model model) {
        model.addAttribute("current_staff", CurrentStaffSingleton.getInstance().getCurrentUser());
        return "StaffModulePage";
    }
}
