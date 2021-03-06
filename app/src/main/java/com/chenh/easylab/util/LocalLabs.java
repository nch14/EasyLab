package com.chenh.easylab.util;

import com.chenh.easylab.vo.LabVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/5.
 */
public class LocalLabs {
    ArrayList<LabVO> labs;
    int postion;

    private static LocalLabs localLabs;
    public static LocalLabs getInstance(){
        if (localLabs == null )
            localLabs=new LocalLabs();
        return localLabs;
    }

    /**
     * 在发送请求前、应该清除旧的labs记录
     */
    public void clear(){
        if (labs!=null)
            labs=null;
    }

    public static void writeInstance(JSONObject js){
        if (localLabs == null )
            localLabs=new LocalLabs();
        localLabs.labs=new ArrayList<>();
        int num= 0;
        try {
            num = js.getInt("num");
            for (int i=0;i<num;i++){
                localLabs.labs.add(new LabVO(new JSONObject(js.getString("item"+i))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void setPostion(int postion){
        this.postion=postion;
    }

    public int getPostion() {
        return postion;
    }

    public ArrayList<LabVO> getLabs(){
        return labs;
    }

}
