package com.chenh.easylab.po;

/**
 * Created by chenh on 2016/7/5.
 */
public class DevicePO {
    public String macAddress;
    public String name;
    public int type;

    public DevicePO(){}

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * Created by chenh on 2016/7/5.
     */
    public static class DeskPO {
        private String tableId;
        private String devices;

        public DeskPO(){}

        public String getTableId() {
            return tableId;
        }

        public void setTableId(String tableId) {
            this.tableId = tableId;
        }

        public String getDevices() {
            return devices;
        }

        public void setDevices(String devices) {
            this.devices = devices;
        }
    }
}
