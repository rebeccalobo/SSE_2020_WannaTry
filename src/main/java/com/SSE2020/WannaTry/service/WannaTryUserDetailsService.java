package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.databaserepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class WannaTryUserDetailsService {
    @Autowired
    private UserRepository userRepository;
}
