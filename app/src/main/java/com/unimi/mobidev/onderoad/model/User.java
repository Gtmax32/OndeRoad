package com.unimi.mobidev.onderoad.model;

import java.io.Serializable;

/**
 * Created by Giuseppe Fabio Trentadue on 30/09/2016.
 */

public class User implements Serializable{

    private String idUser;
    private String nameUser;
    private String emailUser;

    public User(){}

    public User(String ID, String nameUser, String emailUser) {
        this.idUser = ID;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    /*public String getSurnameUser() {
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
    }*/

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String toString(){
        return "User: " +
               "\nName: " + this.nameUser +
               "\nEmail: " + this.emailUser;
    }
}
