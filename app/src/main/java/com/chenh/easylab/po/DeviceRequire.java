package com.chenh.easylab.po;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/5.
 */
public class DeviceRequire {
    private String deviceName;
    private int needNum;
    private boolean defaultDevice;

    public boolean isDefaultDevice() {
        return defaultDevice;
    }

    public void setDefaultDevice(boolean defaultDevice) {
        this.defaultDevice = defaultDevice;
    }

    public DeviceRequire(String deviceName, int needNum){
        this.deviceName=deviceName;
        this.needNum=needNum;
        defaultDevice=true;

    }

    public DeviceRequire(String json){
        try {
            JSONObject js=new JSONObject(json);
            deviceName=js.getString("deviceName");
            needNum= Integer.parseInt(js.getString("needNum"));
            defaultDevice=(js.getString("defaultDevice").equals("defaultDevice"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getJsonObject(){
        JSONObject js=new JSONObject();
        try {
            js.put("deviceName",deviceName);
            js.put("needNum",needNum);
            js.put("defaultDevice",(defaultDevice)?"defaultDevice":"simpleDevice");
            return js;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getNeedNum() {
        return needNum;
    }

}
