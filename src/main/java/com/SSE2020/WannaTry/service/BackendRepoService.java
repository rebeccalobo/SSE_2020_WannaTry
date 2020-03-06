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
    ModuleEnrolmentsRepository moduleEnrolmentsRepository;
    @Autowired
    StudentModuleGradesRepository studentModuleGradesRepository;
    //add the other repos here
    public StaffRepository getStaffRepo() {return staffRepository; }
    public StudentRepository getStudentRepo(){
        return studentRepository;
    }
    public ModuleRepository getModuleRepo() {return moduleRepository;}

    public ModuleEnrolmentsRepository getModuleEnrolmentsRepository() {
        return moduleEnrolmentsRepository;
    }

    public StudentModuleGradesRepository getStudentModuleGradesRepository() {
        return studentModuleGradesRepository;
    }
}
