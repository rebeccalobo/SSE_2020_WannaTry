package com.SSE2020.WannaTry;

public class CurrentUserSingleton {
    private static CurrentUserSingleton currentUserSingleton = null;
    private String curerentUser;
    private CurrentUserSingleton(){
        curerentUser = null;
    }
    public void setCurrentUser(String id){
        curerentUser = id;
    }
    public static CurrentUserSingleton getInstance(){
        if(currentUserSingleton == null){
            currentUserSingleton = new CurrentUserSingleton();
        }
        return currentUserSingleton;
    }
    public String getCurrentUser(){
        return curerentUser;
    }
}
