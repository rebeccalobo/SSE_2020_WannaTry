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

    // Get All Notes
    @GetMapping("/staff")
    public List<Staff> getAllNotes() {
        return repoService.getStaffRepo().findAll();
    }

    // Create a new Note
    @PostMapping("/staff")
    public Staff createNote(@Valid @RequestBody Staff staff) {
        return repoService.getStaffRepo().save(staff);
    }
    // Update a Note
    @PutMapping("/staff/{id}")
    public Staff updateNote(@PathVariable(value = "id") String staffId,
                               @Valid @RequestBody Staff staffDetails) throws StaffNotFoundException {

        Staff staff = repoService.getStaffRepo().findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException(staffId));



        staff.setStaff_firstname(staffDetails.getStaff_firstname());
        staff.setStaff_surname(staffDetails.getStaff_surname());
        staff.setPassword(staffDetails.getPassword());

        return repoService.getStaffRepo().save(staff);
    }
    @RequestMapping(value ="/edit_description", method = RequestMethod.POST)
    public String editDescription(@RequestParam("description") String desc,@RequestParam("module_id")String module_id ) throws StaffNotFoundException, ModuleNotFoundException {
        Modules modules = repoService.getModuleRepo().findById(module_id).orElseThrow(()->new ModuleNotFoundException(module_id));
        modules.setDescription(desc);
        repoService.getModuleRepo().save(modules);
        return "redirect:/StaffDashboard";
    }

    // Delete a Note
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") String staffId) throws StaffNotFoundException {
        Staff staff = repoService.getStaffRepo().findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException(staffId));

        repoService.getStaffRepo().delete(staff);

        return ResponseEntity.ok().build();
    }
    @RequestMapping("/StaffDashboard")
    public String goToStaffDashboard(Model model){
        Staff current_staff = CurrentStaffSingleton.getInstance().getCurrentUser();
        HashMap<String, ArrayList<String>> students_enroled_in_module = new HashMap<>();
        model.addAttribute("current_staff", current_staff);
        ArrayList<Modules> modules = repoService.getModuleRepo().getStaffModules(current_staff.getStaff_id());
        for(Modules m : modules){
            students_enroled_in_module.put(m.getModule_name(),
                    repoService.getModuleRepo().getStudentsInSpecificModuleStaff(m.getModule_id(),current_staff.getStaff_id()));
        }
        model.addAttribute("hashmap",students_enroled_in_module);
        model.addAttribute("modules",modules);
        model.addAttribute("description","");
        return "staffDashboard";
    }




}
