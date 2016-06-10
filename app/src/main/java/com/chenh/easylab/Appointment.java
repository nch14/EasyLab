package com.chenh.easylab;

import com.chenh.easylab.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chenh on 2016/6/5.
 */
public class Appointment {
    private String title;
    private Date start;
    private Date end;

    private String roomId;
    private String tableId;

    private  ArrayList<UserVO> members;
    private  ArrayList<DeviceRequire> deviceRequires;

    private String details;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Appointment(){
        title="测试";
        start=new Date();
        end=new Date();

        roomId="104";
        tableId="100";
        members=new ArrayList<>();
        members.add(new UserVO("mx","***","141250094","孟欣",0));
        members.add(new UserVO("mx","***","141250094","孟欣",0));
        members.add(new UserVO("mx","***","141250094","孟欣",0));
        members.add(new UserVO("mx","***","141250094","孟欣",0));

        deviceRequires=new ArrayList<>();
        deviceRequires.add(new DeviceRequire("锅",2));
        details="借用实验室煮火锅";
        state="已完成";
    }

    public String getTitle() {
        return title;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getTableId() {
        return tableId;
    }

    public ArrayList<UserVO> getMembers() {
        return members;
    }

    public ArrayList<DeviceRequire> getDeviceRequires() {
        return deviceRequires;
    }

    public String getDetails() {
        return details;
    }



}
