package com.chenh.easylab.vo;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/4.
 */
public class LabPO {
    private String roomId;
    private String devices;
    private String desks;


    public LabPO(){}



    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    public String getDesks() {
        return desks;
    }

    public void setDesks(String desks) {
        this.desks = desks;
    }
}
