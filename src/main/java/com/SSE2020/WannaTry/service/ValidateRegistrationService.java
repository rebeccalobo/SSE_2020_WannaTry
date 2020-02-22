package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.model.Students;
import org.springframework.stereotype.Service;

@Service
public class ValidateRegistrationService {
    public boolean validateFields(Students student){
        String fName = student.getStudent_firstname();

        String lName = student.getStudent_surname();
        String email = student.getEmail();
        String address = student.getAddress();
        String number = student.getPhone_number();
        String pwd = student.getPassword();
        String id = student.getStudent_id();


        return (fName.matches("^[a-zA-Z]+$")) &&
                (lName.matches("^[a-zA-Z]+$")) &&
                (email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) &&
                (!address.isEmpty()) &&
                (number.matches("^[0-9]{8,}$")) &&
                (id.matches("^[0-9]{8}")) &&
                (pwd.matches("^[A-Za-z0-9]+$"));
    }
}
