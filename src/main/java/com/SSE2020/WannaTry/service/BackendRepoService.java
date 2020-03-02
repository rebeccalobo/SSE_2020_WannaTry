package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.databaserepo.ModuleRepository;
import com.SSE2020.WannaTry.databaserepo.StaffRepository;
import com.SSE2020.WannaTry.databaserepo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendRepoService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    ModuleRepository moduleRepository;
    //add the other repos here
    public StaffRepository getStaffRepo() {return staffRepository; }
    public StudentRepository getStudentRepo(){
        return studentRepository;
    }
    public ModuleRepository getModuleRepo() {return moduleRepository;}
}
