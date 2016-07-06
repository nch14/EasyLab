package com.chenh.easylab.util;

import com.chenh.easylab.vo.DeskVO;
import com.chenh.easylab.vo.UserVO;

import org.json.JSONObject;

/**
 * Created by chenh on 2016/7/5.
 */
public class LocalUser {
    UserVO user;

    private static LocalUser localUser;
    public static LocalUser getInstance(){
        if (localUser == null )
            localUser =new LocalUser();
        return localUser;
    }

    public void clear(){
        user=null;
    }

    public static void writeInstance(JSONObject js){
        if (localUser == null )
            localUser =new LocalUser();
        localUser.user=new UserVO(js);
    }

    public UserVO getUser(){
        return user;
    }
}
