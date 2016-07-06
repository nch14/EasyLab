package com.chenh.easylab.util;

import com.chenh.easylab.vo.DeskVO;

import org.json.JSONObject;

/**
 * Created by chenh on 2016/7/5.
 */
public class LocalBoolean {
    Boolean success;

    private static LocalBoolean localBoolean;
    public static LocalBoolean getInstance(){
        if (localBoolean == null )
            localBoolean =new LocalBoolean();
        return localBoolean;
    }

    public void clear(){
        success=null;
    }

    public static void writeInstance(boolean bool){
        if (localBoolean == null )
            localBoolean =new LocalBoolean();
        localBoolean.success=bool;
    }

    public Boolean getSuccess(){
        return success;
    }
}
