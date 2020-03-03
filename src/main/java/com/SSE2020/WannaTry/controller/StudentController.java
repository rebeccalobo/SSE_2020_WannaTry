package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Students;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
public class StudentController {

    @Autowired
    BackendRepoService repoService;

    // Get All Notes
    @GetMapping("/students")
    public List<Students> getAllNotes() {
        return repoService.getStudentRepo().findAll();
    }

    // Create a new Note
    @PostMapping("/students")
    public Students createNote(@Valid @RequestBody Students student) {
        return repoService.getStudentRepo().save(student);
    }
    // Update a Note
    @PutMapping("/students/{id}")
    public Students updateNote(@PathVariable(value = "id") String studentId,
                               @Valid @RequestBody Students studentDetails) throws StudentNotFoundException {

        Students student = repoService.getStudentRepo().findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));



        student.setStudent_firstname(studentDetails.getStudent_firstname());
        student.setStudent_surname(studentDetails.getStudent_surname());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());
        student.setPhone_number(studentDetails.getPhone_number());
        student.setPassword(studentDetails.getPassword());


        return repoService.getStudentRepo().save(student);
    }








}
