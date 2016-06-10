package com.chenh.easylab.util;

import com.chenh.easylab.vo.UserVO;

/**
 * Created by chenh on 2016/6/10.
 */
public class CurrentUser {

    private static UserVO user;

    public static void newUser(UserVO user){
        CurrentUser.user=user;
    }

    public static UserVO getUser(){
        return user;
    }

}
