package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@ControllerAdvice
public class StudentController {



//    @Autowired
//    StudentRepository studentRepository;
    @Autowired
    BackendRepoService repoService;

    private Students current_user = null;
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
                               @Valid @RequestBody Students studentDetails) throws StudentNotFoundException, NoSuchAlgorithmException {

        Students student = repoService.getStudentRepo().findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));



        student.setStudent_firstname(studentDetails.getStudent_firstname());
        student.setStudent_surname(studentDetails.getStudent_surname());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());
        student.setPhone_number(studentDetails.getPhone_number());
        student.setPassword(studentDetails.getPassword());

        Students updatedStudent = repoService.getStudentRepo().save(student);

        return updatedStudent;
    }

    // Delete a Note
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") String studentId) throws StudentNotFoundException {
        Students student = repoService.getStudentRepo().findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        repoService.getStudentRepo().delete(student);

        return ResponseEntity.ok().build();
    }




}
