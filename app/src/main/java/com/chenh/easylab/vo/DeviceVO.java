package com.chenh.easylab.vo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chenh on 2016/6/30.
 */
public class DeviceVO {
    public final static int NORMAL_DEVICE=1;
    public final static int AIR_CONDITION=2;
    public final static int AMPEREMETER=3;
    public final static int TEMPSENSOR=4;


    public String macAddress;
    public String name;
    public int type;

    //--------------------------临时字段------------------------------
    public boolean open;
    public double value;
    //----------------------------------------------------------------

    public DeviceVO(){}

    public DeviceVO(String macAddress,String name,int type){
        this.macAddress=macAddress;
        this.name=name;
        this.type=type;
    }

    public DeviceVO(JSONObject device){
        try {
            this.macAddress=device.getString("macAddress");
            this.name=device.getString("name");
            this.type=device.getInt("type");
            LocalDeviceManage.getInstance().addDevice(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject(){
        JSONObject device=new JSONObject();
        return device;
    }
}
