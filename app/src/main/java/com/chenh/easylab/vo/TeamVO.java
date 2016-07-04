package com.chenh.easylab.vo;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by chenh on 2016/6/10.
 */
public class TeamVO {
    public UUID uuid;
    /**
     * 小组类型，允许的值
     */
    public String type;

    public String teamName;

    public ArrayList<String> notification;

    public String creator;

    public ArrayList<String> nameList;

    public TeamVO(String type,String teamName,ArrayList<String> notification,String creator,ArrayList<String> nameList){
        this.uuid=UUID.randomUUID();
        this.teamName=teamName;
        this.type=type;
        this.nameList=nameList;
        this.creator=creator;
        this.notification=notification;
    }

    //伪造数据的构造函数
    public TeamVO(boolean b){
        this.uuid=UUID.randomUUID();
        this.teamName="霍格沃兹魁地奇队";
        this.creator="Harry Potter";
        this.type="创新项目团队";
        notification=new ArrayList<>();
        notification.add("2016.06.22 20：00训练");

        nameList=new ArrayList<>();
        nameList.add("Harry Potter");
        nameList.add("Hermione");
        nameList.add("Ron");
    }

    public TeamVO(JSONObject js){
        try {
            this.uuid=UUID.fromString(js.getString("uuid"));
            this.teamName=js.getString("teamName");
            this.creator=js.getString("creator");
            this.type=js.getString("type");

            JSONObject noJson=new JSONObject(js.getString("notification"));
            this.notification=new ArrayList<>();
            int num1=Integer.parseInt(noJson.getString("num"));
            String item="item";
            for (int i=0;i<num1;i++){
                this.notification.add(noJson.getString(item+i));
            }

            JSONObject naJson=new JSONObject(js.getString("nameList"));
            this.nameList=new ArrayList<>();
            int num2=Integer.parseInt(naJson.getString("num"));
            for (int i=0;i<num2;i++){
                this.nameList.add(naJson.getString(item+i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

  /*  *//**
     * 从数据层解析数据
     * @param uuid
     * @param type
     * @param teamName
     * @param notification
     * @param creator
     * @param nameList
     *//*
    public TeamVO(String uuid,String type,String teamName,String notification,String creator,String nameList){
        this.uuid=UUID.fromString(uuid);
        this.type=type;
        this.teamName=teamName;
        this.creator=creator;

        try {
            JSONObject noJson=new JSONObject(notification);
            this.notification=new ArrayList<>();
            int num1=Integer.parseInt(noJson.getString("num"));
            String item="item";
            for (int i=0;i<num1;i++){
                this.notification.add(noJson.getString(item+i));
            }

            JSONObject naJson=new JSONObject(nameList);
            this.nameList=new ArrayList<>();
            int num2=Integer.parseInt(naJson.getString("num"));
            for (int i=0;i<num2;i++){
                this.nameList.add(noJson.getString(item+i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
*/
    public JSONObject getJsonObject(){
        JSONObject js=new JSONObject();
        try {
            js.put("uuid",uuid);
            js.put("type",type);
            js.put("teamName",teamName);
            js.put("creator",creator);

            JSONObject notifi=new JSONObject();
            notifi.put("num",notification.size());
            for (int i=0;i<notification.size();i++){
                notifi.put("item"+i,notification.get(i));
            }
            js.put("notification",notifi);

            JSONObject name=new JSONObject();
            name.put("num",nameList.size());
            for (int i=0;i<nameList.size();i++){
                name.put("item"+i,nameList.get(i));
            }
            js.put("nameList",name);

            return js;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //js.put()
        return null;
    }
}
