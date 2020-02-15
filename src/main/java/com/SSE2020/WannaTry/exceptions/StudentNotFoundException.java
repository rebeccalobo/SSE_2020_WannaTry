package com.SSE2020.WannaTry.exceptions;

public class StudentNotFoundException extends Exception {
    private Long student_id;
    public StudentNotFoundException(Long student_id){
        super(String.format("No student was found with id: %s", student_id));
    }
}
