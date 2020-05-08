package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.databaserepo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendRepoService {


    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    ModuleEnrolmentsRepository enrolRepository;
    @Autowired
    GradesRepository smgRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;
    //add the other repos here

    public ModuleRepository getModuleRepo() {return moduleRepository;}
    public ModuleEnrolmentsRepository getEnrolRepo() {return enrolRepository;}
    public GradesRepository getSmgRepo() {return smgRepository;}

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public PrivilegeRepository getPrivilegeRepository() {
        return privilegeRepository;
    }


}
