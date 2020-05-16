package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.databaserepo.BlacklistRepository;
import com.SSE2020.WannaTry.databaserepo.LoggedActionsRepository;
import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.IP_Blacklist;
import com.SSE2020.WannaTry.model.Role;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class RegisterController {

    final
    BackendRepoService repoService;
    final
    BlacklistRepository blacklistRepository;
    final
    PasswordEncoder passwordEncoder;
    final LoggedActionsRepository loggedActionsRepository;

    public RegisterController(BackendRepoService repoService, BlacklistRepository blacklistRepository, PasswordEncoder passwordEncoder, LoggedActionsRepository loggedActionsRepository) {
        this.repoService = repoService;
        this.blacklistRepository = blacklistRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedActionsRepository = loggedActionsRepository;
    }


    @GetMapping(value="/Register")
    public String register(Model model, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        if(isBlackListed(ip)){
            return "redirect:/blocked";
        }
        Object context = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("flag", !(context instanceof CustomUserDetails));
        User user = new User();
        model.addAttribute("User",user);
        model.addAttribute("incorrectFormat","correct");


        return "Register";
    }
    private boolean isBlackListed(String IP){
        Optional<IP_Blacklist> blacklistedIP = blacklistRepository.findById(IP);
        return blacklistedIP.map(ip_blacklist -> ip_blacklist.getIP_ADDRESS().equals(IP)).orElse(false);
    }
    @Transactional
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String saveUser(@ModelAttribute("User") User user,HttpServletRequest request){
        ArrayList<Role> roles = new ArrayList<>();





        Role role = repoService.getRoleRepository().findByName("ROLE_STUDENT");
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setID(repoService.getUserRepository().getRandomID());
        repoService.getUserRepository().save(user);
        loggedActionsRepository.log(user.getID(), "REGISTER", "User: " + user.getFName() + " " + user.getLName() + " registered", request.getRemoteAddr(), new Date());

        return "redirect:/Dashboard";
    }

    // Delete a Note
    @Modifying
    @Transactional
    @GetMapping("/unregister")
    public String unregisterUser(HttpServletRequest request){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        int id = user.getID();
        String ip = request.getRemoteAddr();
        loggedActionsRepository.log(id, "UNREGISTER", "User: " + user.getFName() + " " + user.getLName() + " Unregistered", ip, new Date());
        repoService.getUserRepository().deleteUser(id);
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}
