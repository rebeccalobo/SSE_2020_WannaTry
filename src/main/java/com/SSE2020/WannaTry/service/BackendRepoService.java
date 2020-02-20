package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.databaserepo.StudentRepository;
import com.SSE2020.WannaTry.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendRepoService {
    @Autowired
    StudentRepository studentRepository;
    //add the other rops here

    public StudentRepository getStudentRepo(){
        return studentRepository;
    }
}
