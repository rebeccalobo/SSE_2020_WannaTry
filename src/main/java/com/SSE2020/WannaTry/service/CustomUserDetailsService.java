package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.databaserepo.UserRepository;
import com.SSE2020.WannaTry.exceptions.BlockedIPException;
import com.SSE2020.WannaTry.exceptions.UserNotFoundException;
import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.security.PrivateKey;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

    public CustomUserDetailsService(UserRepository userRepository,HttpServletRequest httpServletRequest) {
        this.userRepository = userRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username){
        Optional<User> opt = userRepository.findById(Integer.valueOf(username));
        try {
            opt.orElseThrow(()->new UserNotFoundException(username));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return opt.map(CustomUserDetails::new).get();
    }


}
