package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyAuthenticationRequest implements Authentication {
    private final User user;
    private boolean isAuthenticated;
    public MyAuthenticationRequest(User user){
        this.user = user;
        isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public User getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated(){
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        isAuthenticated = b;
    }


    @Override
    public String getName() {
        return user.getFName() + " " + user.getLName();
    }
}
