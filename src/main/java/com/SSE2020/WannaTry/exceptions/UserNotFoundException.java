package com.SSE2020.WannaTry.exceptions;

public class UserNotFoundException extends Exception {
    private Long student_id;
    public UserNotFoundException(String student_id){
        super(String.format("No student was found with id: %s", student_id));
    }
}
