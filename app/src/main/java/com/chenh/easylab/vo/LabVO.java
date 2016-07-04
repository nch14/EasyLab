package com.chenh.easylab.vo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/3.
 */
public class LabVO {

    public String roomId;
    public ArrayList<DeviceVO> devices;
    public ArrayList<DeskVO> desks;

    public LabVO(){}

    public LabVO(String roomId){
        this.roomId=roomId;
        devices=new ArrayList<>();
        desks=new ArrayList<>();
    }

    public LabVO(JSONObject lab){
        try {
            this.roomId=lab.getString("roomId");
            JSONObject devices=lab.getJSONObject("devices");
            this.devices=new ArrayList<>();
            int num1=devices.getInt("num");
            for (int i=0;i<num1;i++){
                this.devices.add(new DeviceVO(devices.getJSONObject("item"+i)));
            }

            JSONObject desks=lab.getJSONObject("desks");
            this.desks=new ArrayList<>();
            int num2=devices.getInt("num");
            for (int i=0;i<num2;i++){
                this.desks.add(new DeskVO(desks.getJSONObject("item"+i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public JSONObject toJsonObject(){
        JSONObject lab=new JSONObject();
        try {
            lab.put("roomId",roomId);
            JSONObject devices=new JSONObject();
            devices.put("num",this.devices.size());
            for (int i=0;i<this.devices.size();i++){
                devices.put("item"+i,this.devices.get(i).toJsonObject());
            }
            lab.put("devices",devices);

            JSONObject desks=new JSONObject();
            desks.put("num",this.desks.size());
            for (int i=0;i<this.desks.size();i++){
                desks.put("item"+i,this.desks.get(i).toJsonObject());
            }
            lab.put("desks",desks);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return lab;
    }

}
