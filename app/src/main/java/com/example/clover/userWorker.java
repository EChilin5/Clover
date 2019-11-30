package com.example.clover;

import android.app.Application;

public class userWorker extends Application {
    private String usernameGlobal;
    private String passwordGlobal;

    public String getUsername(){
        return usernameGlobal;
    }
    public String getPassword(){
        return passwordGlobal;

    }
    public void setUsername(String usernameGlobal){
        this.usernameGlobal=usernameGlobal;
    }
    public void setPassword(String passwordGlobal){
        this.passwordGlobal=passwordGlobal;


    }
}
