package com.chenh.easylab.vo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/3.
 */
public class DeskVO {

    public String tableId;
    public ArrayList<DeviceVO> devices;

    public DeskVO(JSONObject desk){
        try {
            this.tableId=desk.getString("tableId");
            JSONObject devices=desk.getJSONObject("devices");
            this.devices=new ArrayList<>();
            int num1=devices.getInt("num");
            for (int i=0;i<num1;i++){
                this.devices.add(new DeviceVO(devices.getJSONObject("item"+i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject(){
        JSONObject desk=new JSONObject();
        try {
            desk.put("tableId",tableId);
            JSONObject devices=new JSONObject();
            devices.put("num",this.devices.size());
            for (int i=0;i<this.devices.size();i++){
                devices.put("item"+i,this.devices.get(i).toJsonObject());
            }
            desk.put("devices",devices);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desk;
    }
}
