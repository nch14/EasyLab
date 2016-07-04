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
    private HashMap<String,Double> amperemeters;

    private static LocalAmperemeters localAmperemeters;
    public static LocalAmperemeters getInstance(){
        if (localAmperemeters == null )
            localAmperemeters =new LocalAmperemeters();
        return localAmperemeters;
    }

    /**
     * 在发送请求前、应该清除旧的labs记录
     */
    public void clear(){
        amperemeters.clear();
    }

    public static void writeInstance(JSONObject js){
        if (localAmperemeters == null )
            localAmperemeters =new LocalAmperemeters();
        try {
            String key=js.getString("key");
            Double value=js.getDouble("value");
            localAmperemeters.amperemeters.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private LocalAmperemeters(){
        amperemeters=new HashMap<>();
    }
}
