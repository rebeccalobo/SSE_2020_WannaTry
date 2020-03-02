package com.SSE2020.WannaTry.exceptions;

public class StaffNotFoundException extends Exception {
    private Long staff_id;
    public StaffNotFoundException(String staff_id){
        super(String.format("No member of staff was found with id: %s", staff_id));
    }
}
