package SpringApp.controller;

import SpringApp.databaserepo.StudentRepository;
import SpringApp.exceptions.StudentNotFoundException;
import SpringApp.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StudentRepository studentRepository;

    // Get All Notes
    @GetMapping("/students")
    public List<Students> getAllNotes() {
        return studentRepository.findAll();
    }
    // Get a Single Note
    @GetMapping("/students/{id}")
    public Students getNoteById(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }
    // Create a new Note
    @PostMapping("/students")
    public Students createNote(@Valid @RequestBody Students student) {
        return studentRepository.save(student);
    }
    // Update a Note
    @PutMapping("/students/{id}")
    public Students updateNote(@PathVariable(value = "id") Long studentId,
                               @Valid @RequestBody Students studentDetails) throws StudentNotFoundException {

        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        student.setStudent_firstname(studentDetails.getStudent_firstname());
        student.setStudent_surname(studentDetails.getStudent_surname());
        student.setEmail(studentDetails.getEmail());

        Students updatedStudent = studentRepository.save(student);

        return updatedStudent;
    }

    // Delete a Note
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        studentRepository.delete(student);

        return ResponseEntity.ok().build();
    }
}
