package com.chenh.easylab;

/**
 * Created by chenh on 2016/6/5.
 */
public class DeviceRequire {
    private String deviceName;
    private int needNum;
    private boolean defaultDevice;

    public boolean isDefaultDevice() {
        return defaultDevice;
    }

    public void setDefaultDevice(boolean defaultDevice) {
        this.defaultDevice = defaultDevice;
    }

    public DeviceRequire(String deviceName, int needNum){
        this.deviceName=deviceName;
        this.needNum=needNum;
        defaultDevice=true;

    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getNeedNum() {
        return needNum;
    }
}
