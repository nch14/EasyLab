package com.chenh.easylab.vo;

import com.chenh.easylab.po.DeviceRequire;
import com.chenh.easylab.vo.UserVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by chenh on 2016/6/5.
 */
public class AppointmentVO {

    private String uuid;

    private String title;
    private String time;

    private String roomId;
    private String tableId;

    private  ArrayList<String> members;
    private  ArrayList<DeviceRequire> deviceRequires;

    private String details;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public AppointmentVO(){
        title="实验";
        time="2016-06-20 15：00-18：00";
        roomId="401";
        tableId="101";
        members=new ArrayList<>();
        members.add("小A");
        members.add("小B");
        members.add("小C");
        deviceRequires=new ArrayList<>();
        details="无";
        state="待审批";
    }

    public AppointmentVO(String time,String title,String roomId,String tableId,ArrayList<String> members,ArrayList<DeviceRequire> deviceRequires,
                         String details){
        this.uuid=UUID.randomUUID().toString();
        this.title=title;
        this.time=time;
        this.roomId=roomId;
        this.tableId=tableId;
        this.members=members;
        this.deviceRequires=deviceRequires;
        this.details=details;
        this.state="待审批";
    }

    public AppointmentVO(JSONObject js){
        try {
            uuid=js.getString("uuid");
            title=js.getString("title");
            time=js.getString("time");
            roomId=js.getString("roomId");
            tableId=js.getString("tableId");

            JSONObject mem=new JSONObject(js.getString("members"));
            int num= Integer.parseInt(mem.getString("num"));
            members=new ArrayList<>();
            for (int i=0;i<num;i++){
                members.add(mem.getString("item"+i));
            }

            JSONObject dev=new JSONObject(js.getString("deviceRequires"));
            num=Integer.parseInt(dev.getString("num"));
            deviceRequires=new ArrayList<>();
            for (int i=0;i<num;i++){
                deviceRequires.add(new DeviceRequire(dev.getString("item"+"i")));
            }

            details=js.getString("details");
            state=js.getString("state");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getJSONObject(){
        JSONObject js=new JSONObject();
        try {
            js.put("title",title);
            js.put("time",time);
            js.put("roomId",roomId);
            js.put("tableId",tableId);

            js.put("uuid",uuid);
            js.put("details",details);
            js.put("state",state);

            JSONObject membersJSON=new JSONObject();
            for (int i=0;i<members.size();i++){
                membersJSON.put("item"+i,members.get(i));
            }
            membersJSON.put("num",members.size());
            js.put("members",membersJSON.toString());

            JSONObject deviceJSON=new JSONObject();
            for (int i=0;i<deviceRequires.size();i++){
                deviceJSON.put("item"+i,deviceRequires.get(i).getJsonObject().toString());
            }
            deviceJSON.put("num",deviceRequires.size());
            js.put("deviceRequires",deviceJSON.toString());

            return js;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //js.put()
        return null;
    }
    /*public String [][] toArray(){
        try {
            JSONObject membersJSON=new JSONObject();
            for (int i=0;i<members.size();i++){
                membersJSON.put("item"+i,members.get(i).getJsonObject().toString());
            }
            membersJSON.put("num",members.size());

            JSONObject deviceJSON=new JSONObject();
            for (int i=0;i<deviceRequires.size();i++){
                deviceJSON.put("item"+i,deviceRequires.get(i).getJsonObject().toString());
            }
            deviceJSON.put("num",deviceRequires.size());


            String[][] forJson=new String[][]{
                    new String[]{"uuid",uuid},
                    new String[]{"title",title},
                    new String[]{"start",start.toString()},
                    new String[]{"end",end.toString()},
                    new String[]{"roomId",roomId},
                    new String[]{"tableId",tableId},
                    new String[]{"details",details},
                    new String[]{"state",state},
                    new String[]{"members",membersJSON.toString()},
                    new String[]{"deviceRequires",deviceJSON.toString()},
            };
            return forJson;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }*/

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getTableId() {
        return tableId;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public ArrayList<DeviceRequire> getDeviceRequires() {
        return deviceRequires;
    }

    public String getDetails() {
        return details;
    }



}
