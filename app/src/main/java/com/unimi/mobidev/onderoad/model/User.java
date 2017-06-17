package com.unimi.mobidev.onderoad.model;

import java.io.Serializable;

/**
 * Created by Giuseppe Fabio Trentadue on 30/09/2016.
 */

public class User implements Serializable{

    private String idUser;
    private String nameUser;
    private String emailUser;
    private String notificationIdUser;

    public User(){}

    public User(String idUser, String nameUser, String emailUser, String notificationIdUser) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.notificationIdUser = notificationIdUser;
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

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getNotificationIdUser() {
        return notificationIdUser;
    }

    public void setNotificationIdUser(String notificationIdUser) {
        this.notificationIdUser = notificationIdUser;
    }

    public String toString(){
        return "User: " +
               "\nName: " + this.nameUser +
               "\nEmail: " + this.emailUser;
    }
}
