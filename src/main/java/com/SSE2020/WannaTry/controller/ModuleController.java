package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.databaserepo.ModuleEnrolmentsRepository;
import com.SSE2020.WannaTry.exceptions.EnrolementNotFoundException;
import com.SSE2020.WannaTry.exceptions.ModuleNotFoundException;
import com.SSE2020.WannaTry.model.ModuleEnrolment;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
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

    //ENROL AND UNENROL
    @RequestMapping(value = "/enrol",method = RequestMethod.POST)
    public String enrol(@RequestParam("student")String student,@RequestParam("module")String module ){
        ModuleEnrolment me = new ModuleEnrolment(module+"/"+student,module,student);
        repoService.getEnrolRepo().save(me);
        return "redirect:/studentDashboard";
    }
    @RequestMapping(value = "/un_enrol",method = RequestMethod.POST)
    public String UnEnrol(Model model, @RequestParam("student") String student,@RequestParam("module")String module){
        try{
            ModuleEnrolment me = repoService.getEnrolRepo().findById(module+"/"+student).orElseThrow(()->new EnrolementNotFoundException(module+"/"+student));
            repoService.getEnrolRepo().delete(me);

        }catch (EnrolementNotFoundException e){
            model.addAttribute("error",e);
            return "error";
        }
        return "redirect:/studentDashboard";
    }
}
