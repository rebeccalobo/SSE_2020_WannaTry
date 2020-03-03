package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StaffNotFoundException;
import com.SSE2020.WannaTry.model.Staff;
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
        model.addAttribute("current_staff", CurrentStaffSingleton.getInstance().getCurrentUser());
        return "staffDashboard";
    }




}
