package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.databaserepo.BlacklistRepository;
import com.SSE2020.WannaTry.databaserepo.LoginAttemptRepository;
import com.SSE2020.WannaTry.model.BLACKLIST;
import com.SSE2020.WannaTry.model.FailedAttempts;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    BackendRepoService repoService;
    @Autowired
    LoginAttemptRepository loginAttemptRepository;
    @Autowired
    BlacklistRepository blacklistRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private final int MAX_ATTEMPTS = 5;

    @GetMapping(value = "/blocked")
    public String routeToBlockedPage(){
        return "BlockedError";
    }

    @RequestMapping(value = "/Login",method = RequestMethod.GET)
    public String showLoginPage(Model model,HttpServletRequest request){
        if(isBlackListed(request.getRemoteAddr())){
            return "redirect:/blocked";
        }

        User user = new User();
        model.addAttribute("flag",true);
        model.addAttribute("user",user);
        return "Login";
    }

    @GetMapping(value = "/LoginSuccess")
    public String redirectToDashBoard(HttpSession session){
        session.setMaxInactiveInterval(120);
        return "redirect:/Dashboard";
    }

    @Transactional
    @PostMapping(value="/LoginFailed")
    public String handleFailedLogin(HttpServletRequest request,Model model){
        String ip = request.getRemoteAddr();
        if(isBlackListed(ip)){
            return "redirect:/blocked";
        }



        Optional<FailedAttempts> optAttempt = loginAttemptRepository.findById(ip);
        if(optAttempt.isPresent()){

            FailedAttempts failedAttempts = optAttempt.get();
            int attempts = failedAttempts.getATTEMPTS() + 1;
            if(attempts >=MAX_ATTEMPTS){
                blockIP(failedAttempts.getIP_ADDRESS());
                return "redirect:/blocked";
            }
            loginAttemptRepository.updateAttempt(ip,attempts,new Date());
        }else{
            Date date = new Date();
            FailedAttempts failedAttempts = new FailedAttempts(ip,1,date);
            loginAttemptRepository.save(failedAttempts);
        }
        User user = new User();
        model.addAttribute("flag",true);
        model.addAttribute("user",user);
        model.addAttribute("failed_attempt",true);
        return "redirect:/Login";
    }

    private boolean isBlackListed(String IP){
        Optional<BLACKLIST> blacklistedIP = blacklistRepository.findById(IP);
        if(blacklistedIP.isPresent()){
            return true;
        }
        return false;
    }

    @Transactional
    void blockIP(String IP){
        blacklistRepository.blockIP(IP);
        Optional<FailedAttempts> failedAttempts = loginAttemptRepository.findById(IP);
        loginAttemptRepository.delete(failedAttempts.get());
    }
}

