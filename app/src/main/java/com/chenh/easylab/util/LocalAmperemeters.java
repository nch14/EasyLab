package com.chenh.easylab.util;

import com.chenh.easylab.vo.LabVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chenh on 2016/7/5.
 */
public class LocalAmperemeters {
    private ArrayList<String> macAddress;
    private ArrayList<Double> values;

    private static LocalAmperemeters localAmperemeters;
    public static LocalAmperemeters getInstance(){
        if (localAmperemeters == null )
            localAmperemeters =new LocalAmperemeters();
        return localAmperemeters;
    }

    public static void writeInstance(JSONObject js){
        if (localAmperemeters == null )
            localAmperemeters =new LocalAmperemeters();
        try {
            String key=js.getString("key");
            Double value=js.getDouble("value");
            //如果已经存在这只表的缓存、就只刷新数据
            if (localAmperemeters.macAddress.contains(key)){
                localAmperemeters.values.set(localAmperemeters.macAddress.indexOf(key),value);
            }else {
                localAmperemeters.macAddress.add(key);
                localAmperemeters.values.add(value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private LocalAmperemeters(){
        macAddress=new ArrayList<>();
        values=new ArrayList<>();
        refresher();
    }

    public void refresher(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //向服务器请求所有的电流表的情况、实时维护到本地
                    for (int i=0;i<macAddress.size();i++){
                        JSONObject js=new JSONObject();
                        try {
                            js.put("op","2001");
                            js.put("macAddress",macAddress.get(i));
                            Client.getInstance().sendRequest(js);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    //等待2秒以后再次刷新数据
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }




}
