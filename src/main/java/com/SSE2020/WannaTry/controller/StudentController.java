package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.CurrentUserSingleton;
import com.SSE2020.WannaTry.databaserepo.StudentRepository;
import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
public class StudentController {



    @Autowired
    StudentRepository studentRepository;

    private Students current_user = null;
    // Get All Notes
    @GetMapping("/students")
    public List<Students> getAllNotes() {
        return studentRepository.findAll();
    }
    // Login
    @RequestMapping(value = "/login_user",method = RequestMethod.POST)
    public String getStudentById(@ModelAttribute("user")Students student, ModelMap model) throws StudentNotFoundException {
        String studentId = student.getStudent_id();
        current_user = studentRepository.findById(studentId)
                .orElseThrow(()->new StudentNotFoundException(studentId));
        model.addAttribute("current_user",current_user);
        CurrentUserSingleton.getInstance().setCurrentUser(studentId);
        return "studentDashboard";
    }
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student")Students student){
        studentRepository.save(student);
        return "studentDashboard";
    }
    // Create a new Note
    @PostMapping("/students")
    public Students createNote(@Valid @RequestBody Students student) {
        return studentRepository.save(student);
    }
    // Update a Note
    @PutMapping("/students/{id}")
    public Students updateNote(@PathVariable(value = "id") String studentId,
                               @Valid @RequestBody Students studentDetails) throws StudentNotFoundException {

        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        student.setStudent_firstname(studentDetails.getStudent_firstname());
        student.setStudent_surname(studentDetails.getStudent_surname());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());
        student.setPhone_number(studentDetails.getPhone_number());
        student.setPassword(studentDetails.getPassword());

        Students updatedStudent = studentRepository.save(student);

        return updatedStudent;
    }

    // Delete a Note
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") String studentId) throws StudentNotFoundException {
        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        studentRepository.delete(student);

        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/studentDashboard")
    public String goToDashboardPage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", current_user);
        return "studentDashboard";
    }
    @RequestMapping(value = "/StudentModule")
    public String goToModulePage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", current_user);
        return "StudentModule";
    }
    @RequestMapping(value = "/Payments")
    public String goToPaymentPage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", current_user);
        return "Payments";
    }
    @RequestMapping(value = "/StudentGradesAndFeedbackPage")
    public String goToGradesPage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", current_user);
        return "StudentGradesAndFeedbackPage";
    }
    @RequestMapping(value="/logout")
    public String logout(){
        current_user = null;
        return "redirect:/Home";
    }
}