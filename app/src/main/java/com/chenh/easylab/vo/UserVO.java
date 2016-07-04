package com.chenh.easylab.vo;

import com.chenh.easylab.po.UserPO;
import com.chenh.easylab.util.Client;
import com.chenh.easylab.util.ServerBackData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/10.
 */
public class UserVO {
    public String username;

    public String password;

    public String sid;

    public String name;

    public String identify;

    public ArrayList<AppointmentVO> appointments;

    public ArrayList<TeamVO> teams;

    public UserVO(UserPO po){
        username=po.getUsername();
        password=po.getPassword();
        sid =po.getsID();
        name=po.getName();

        switch (po.getIdentify()){
            case 1:
                identify="学生";
                break;
            case 2:
                identify="教师";
                break;
            case 3:
                identify="管理员";
                break;
        }

    }

    public UserVO(String username,String password,String sID,String name,int identify){
        this.username=username;
        this.password=password;
        this.sid =sID;
        this.name=name;

        switch (identify){
            case 1:
                this.identify="学生";
                break;
            case 2:
                this.identify="教师";
                break;
            case 3:
                this.identify="管理员";
                break;
        }
        this.appointments=new ArrayList<>();
        this.teams=new ArrayList<>();
    }
  /*  public UserVO(String username,String password,String sid,String name,int identify,String appointments,String teams){
        this.username=username;
        this.password=password;
        this.sid=sid;
        this.name=name;

        switch (identify){
            case 1:
                this.identify="学生";
                break;
            case 2:
                this.identify="教师";
                break;
            case 3:
                this.identify="管理员";
                break;
        }

        try {
            JSONObject apJson=new JSONObject(appointments);
            this.appointments=new ArrayList<>();
            int num=Integer.parseInt(apJson.getString("num"));
            String item="item";
            for (int i=0;i<num;i++){
                this.appointments.add(UUID.fromString(apJson.getString(item+i)));
            }

            apJson=new JSONObject(teams);
            this.teams=new ArrayList<>();
            num=Integer.parseInt(apJson.getString("num"));
            item="item";
            for (int i=0;i<num;i++){
                this.teams.add(UUID.fromString(apJson.getString(item+i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
    public UserVO(JSONObject js){
        try {
            String username=js.getString("username");
            String password=js.getString("password");
            String name=js.getString("name");
            String sid=js.getString("sid");
            String appointments=js.getString("appointments");
            String teams=js.getString("teams");
            int identify=Integer.parseInt(js.getString("identify"));

            this.username=username;
            this.password=password;
            this.sid =sid;
            this.name=name;

            switch (identify){
                case 1:
                    this.identify="学生";
                    break;
                case 2:
                    this.identify="教师";
                    break;
                case 3:
                    this.identify="管理员";
                    break;
            }

            JSONObject apJson=new JSONObject(appointments);
            this.appointments=new ArrayList<>();
            int num=Integer.parseInt(apJson.getString("num"));
            String item="item";
            for (int i=0;i<num;i++){
                this.appointments.add(new AppointmentVO(new JSONObject(apJson.getString(item+i))));
            }

            apJson=new JSONObject(teams);
            this.teams=new ArrayList<>();
            num=Integer.parseInt(apJson.getString("num"));
            item="item";
            for (int i=0;i<num;i++){
                this.teams.add(new TeamVO(new JSONObject(apJson.getString(item+i))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addAppointment(AppointmentVO appointmentVO){
        this.appointments.add(appointmentVO);
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject js=getJsonObject();
                try {
                    js.put("op",3);
                    ServerBackData serverBackData=Client.register(js);
                    if (serverBackData.isResultState()){
                        //发送成功
                    }else{
                        //发送失败
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public String[][] getData(){
        try {
            JSONObject appoint=new JSONObject();
            appoint.put("num",appointments.size());
            for (int i=0;i<appointments.size();i++){
                appoint.put("item"+i,appointments.get(i));
            }
            String[] js1=new String[]{"appointments",appoint.toString()};

            JSONObject team=new JSONObject();
            team.put("num",teams.size());
            for (int i=0;i<teams.size();i++){
                team.put("item"+i,teams.get(i).getJsonObject());
            }
            String[] js2=new String[]{"teams",team.toString()};

            String[][] content=new String[][]{
                    new String[]{"username",username},
                    new String[]{"password",password},
                    new String[]{"sid", sid},
                    new String[]{"name",name},
                    new String[]{"identify",identify},
                    js1,
                    js2
            };
            return content;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJsonObject(){
        JSONObject js=new JSONObject();
        try {
            js.put("username",username);
            js.put("password",password);
            js.put("sid", sid);
            js.put("name",name);
            js.put("identify",identify);

            JSONObject appoint=new JSONObject();
            appoint.put("num",appointments.size());
            for (int i=0;i<appointments.size();i++){
                appoint.put("item"+i,appointments.get(i));
            }
            js.put("appointments",appoint);

            JSONObject team=new JSONObject();
            team.put("num",teams.size());
            for (int i=0;i<teams.size();i++){
                team.put("item"+i,teams.get(i).getJsonObject());
            }
            js.put("teams",team);

            return js;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //js.put()
        return null;
    }
    

}
