package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.databaserepo.*;
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
    @Autowired
    ModuleEnrolmentsRepository enrolRepository;
    @Autowired
    StudentModuleGradesRepository smgRepository;
    //add the other repos here
    public StaffRepository getStaffRepo() {return staffRepository; }
    public StudentRepository getStudentRepo(){
        return studentRepository;
    }
    public ModuleRepository getModuleRepo() {return moduleRepository;}
    public ModuleEnrolmentsRepository getEnrolRepo() {return enrolRepository;}
    public StudentModuleGradesRepository getSmgRepo() {return smgRepository;}
}
