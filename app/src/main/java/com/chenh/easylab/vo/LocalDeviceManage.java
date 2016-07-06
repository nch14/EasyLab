package com.chenh.easylab.vo;

import com.chenh.easylab.util.Client;
import com.chenh.easylab.util.ServerBackData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/6.
 */
public class LocalDeviceManage {
    ArrayList<DeviceVO> deviceVOs;

    private static LocalDeviceManage localDeviceManage;

    public static LocalDeviceManage getInstance() {
        if (localDeviceManage == null)
            localDeviceManage = new LocalDeviceManage();
        return localDeviceManage;
    }

    private LocalDeviceManage() {
        deviceVOs = new ArrayList<>();
        refresher();
    }

    public void addDevice(DeviceVO deviceVO){
        deviceVOs.add(deviceVO);
    }


    public void refresher() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    for (int i=0;i<deviceVOs.size();i++){
                        DeviceVO d=deviceVOs.get(i);
                        JSONObject js=new JSONObject();
                        boolean sendSuccess;
                        try {
                            switch (d.type){
                                case DeviceVO.AIR_CONDITION:
                                    break;
                                case DeviceVO.NORMAL_DEVICE:
                                    js.put("op","3001");
                                    js.put("macAddress",d.macAddress);
                                    sendSuccess=Client.getInstance().sendRequest(js);
                                    if (sendSuccess){
                                        ServerBackData serverBackData=Client.getInstance().receiveData();
                                        if (!serverBackData.isResultState()){
                                            //Toast.makeText(getActivity(),"服务器无响应",Toast.LENGTH_SHORT).show();
                                        }else {
                                            Boolean state= (Boolean) serverBackData.getObject();
                                            d.open=state;
                                        }
                                    }
                                    break;
                                case DeviceVO.AMPEREMETER:
                                    js.put("op","2001");
                                    js.put("macAddress",d.macAddress);
                                    sendSuccess=Client.getInstance().sendRequest(js);
                                    if (sendSuccess){
                                        ServerBackData serverBackData=Client.getInstance().receiveData();
                                        if (!serverBackData.isResultState()){
                                            //Toast.makeText(getActivity(),"服务器无响应",Toast.LENGTH_SHORT).show();
                                        }else {
                                            Double aDouble= (Double) serverBackData.getObject();
                                            d.value=aDouble;
                                        }
                                    }
                                    break;
                                case DeviceVO.TEMPSENSOR:
                                    js.put("op","5001");
                                    js.put("macAddress",d.macAddress);
                                    sendSuccess=Client.getInstance().sendRequest(js);
                                    if (sendSuccess){
                                        ServerBackData serverBackData=Client.getInstance().receiveData();
                                        if (!serverBackData.isResultState()){
                                            //Toast.makeText(getActivity(),"服务器无响应",Toast.LENGTH_SHORT).show();
                                        }else {
                                            Double aDouble= (Double) serverBackData.getObject();
                                            d.value=aDouble;
                                        }
                                    }
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ).start();
    }
}
