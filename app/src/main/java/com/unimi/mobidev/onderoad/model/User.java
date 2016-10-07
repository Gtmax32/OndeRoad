package com.unimi.mobidev.onderoad.model;

import java.io.Serializable;

/**
 * Created by Giuseppe Fabio Trentadue on 30/09/2016.
 */

public class User implements Serializable{

    private String nameUser;
    private String surnameUser;
    private String facebookIdUser;
    private String emailUser;

    public User(String nameUser, String surnameUser, String facebookIdUser, String emailUser) {
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.facebookIdUser = facebookIdUser;
        this.emailUser = emailUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public void setSurnameUser(String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public String getFacebookIdUser() {
        return facebookIdUser;
    }

    public void setFacebookIdUser(String facebookIdUser) {
        this.facebookIdUser = facebookIdUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String toString(){
        return "User: " +
               "\nName: " + this.nameUser +
               "\nSurname: " + this.surnameUser +
               "\nFacebook ID: " + this.facebookIdUser +
               "\nEmail: " + this.emailUser;
    }
}
