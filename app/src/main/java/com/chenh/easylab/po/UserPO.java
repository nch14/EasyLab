package com.chenh.easylab.po;

import java.io.Serializable;

/**
 * Created by chenh on 2016/6/9.
 */
public class UserPO implements Serializable {

    private String username;

    private String password;

    private String sID;

    private String name;

    private int identify;

    private String teams;

    private String appointments;

    public int getIdentify() {
        return identify;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public UserPO(String username, String password, String sID, String name){
        this.username=username;
        this.password=password;
        this.sID=sID;
        this.name=name;
    }

    public UserPO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
