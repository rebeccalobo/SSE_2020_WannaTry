package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.model.Students;

public class CurrentUserSingleton {
    private static CurrentUserSingleton currentUserSingleton = null;
    private Students curerentUser;
    private CurrentUserSingleton(){
        curerentUser = null;
    }
    public void setCurrentUser(Students student){
        curerentUser = student;
    }
    public static CurrentUserSingleton getInstance(){
        if(currentUserSingleton == null){
            currentUserSingleton = new CurrentUserSingleton();
        }
        return currentUserSingleton;
    }
    public Students getCurrentUser(){
        return curerentUser;
    }
}
