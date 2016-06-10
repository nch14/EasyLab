package com.chenh.easylab.vo;

import com.chenh.easylab.po.UserPO;

/**
 * Created by chenh on 2016/6/10.
 */
public class UserVO {
    public String username;

    public String password;

    public String sID;

    public String name;

    public String identify;

    public UserVO(UserPO po){
        username=po.getUsername();
        password=po.getPassword();
        sID=po.getsID();
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
        this.sID=sID;
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

    }

}
