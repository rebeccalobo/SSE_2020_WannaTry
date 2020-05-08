package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.databaserepo.PrivilegeRepository;
import com.SSE2020.WannaTry.databaserepo.RoleRepository;
import com.SSE2020.WannaTry.databaserepo.UserRepository;
import com.SSE2020.WannaTry.model.Privilege;
import com.SSE2020.WannaTry.model.Role;
import com.SSE2020.WannaTry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    boolean previouslySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(previouslySetup) return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege maintenancePrivilege = createPrivilegeIfNotFound("MAINTENANCE_PRIVILEGE");

        List<Privilege> adminPriveliges = Arrays.asList(readPrivilege,writePrivilege,maintenancePrivilege);
        createRoleIfNotFound("ROLE_ADMIN",adminPriveliges);
        createRoleIfNotFound("ROLE_STUDENT", Arrays.asList(readPrivilege));
        createRoleIfNotFound("ROLE_STAFF",Arrays.asList(writePrivilege,readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFName("Kiowa");
        user.setLName("Daly");
        user.setAddress("17 Cherryfield, Arklow");
        user.setEmail("kiowa.daly@ucdconnect.ie");
        user.setDOB(new Date(1997-12-23));
        user.setPassword(passwordEncoder.encode("TEST123"));
        user.setGender('M');
        user.setEthnicity("White");
        user.setRoles(Arrays.asList(adminRole));
        user.setPhoneNumber("0894940198");
        userRepository.save(user);

        previouslySetup = true;
    }

    @Transactional
    protected Privilege createPrivilegeIfNotFound(String name){
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null){
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
    @Transactional
    protected Role createRoleIfNotFound(String name, Collection<Privilege> privilegeCollection){
        Role role = roleRepository.findByName(name);
        if(role==null){
            role = new Role(name);
            role.setPrivileges(privilegeCollection);
            roleRepository.save(role);
        }
        return role;
    }
}


