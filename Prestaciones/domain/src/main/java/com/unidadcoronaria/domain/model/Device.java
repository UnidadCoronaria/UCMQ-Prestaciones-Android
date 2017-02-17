package com.unidadcoronaria.domain.model;

import java.io.Serializable;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class Device extends BaseModel implements Serializable{

    private Integer deviceId;
    private String name;
    private String type;
    private String imei;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
