package com.chenh.easylab.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by chenh on 2016/7/5.
 */
public class LocalPowers {
    private HashMap<String,Boolean> powers;

    private static LocalPowers localPowers;
    public static LocalPowers getInstance(){
        if (localPowers == null )
            localPowers =new LocalPowers();
        return localPowers;
    }

    /**
     * 在发送请求前、应该清除旧的labs记录
     */
    public void clear(){
        powers.clear();
    }

    public static void writeInstance(JSONObject js){
        if (localPowers == null )
            localPowers =new LocalPowers();
        try {
            String key=js.getString("key");
            Boolean value=js.getBoolean("value");
            localPowers.powers.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private LocalPowers(){
        powers=new HashMap<>();
    }
}
