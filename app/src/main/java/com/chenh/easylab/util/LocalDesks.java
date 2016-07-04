package com.chenh.easylab.util;

import com.chenh.easylab.vo.DeskVO;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chenh on 2016/7/5.
 */
public class LocalDesks {
    DeskVO desk;

    private static LocalDesks localDesks;
    public static LocalDesks getInstance(){
        if (localDesks == null )
            localDesks =new LocalDesks();
        return localDesks;
    }

    public static void writeInstance(JSONObject js){
        if (localDesks == null )
            localDesks =new LocalDesks();
        localDesks.desk=new DeskVO(js);
    }
}
