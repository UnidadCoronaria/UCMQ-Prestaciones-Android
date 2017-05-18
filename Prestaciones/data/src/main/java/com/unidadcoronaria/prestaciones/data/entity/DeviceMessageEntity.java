package com.unidadcoronaria.prestaciones.data.entity;

import java.util.Date;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class DeviceMessageEntity extends BaseEntity {

    private Integer deviceMessageId;
    private Long dateTime;
    private String message;
    private DeviceEntity device;
    private GuardEntity guard;
    private char sendCallcenter;

    public Integer getDeviceMessageId() {
        return deviceMessageId;
    }

    public void setDeviceMessageId(Integer deviceMessageId) {
        this.deviceMessageId = deviceMessageId;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    public GuardEntity getGuard() {
        return guard;
    }

    public void setGuard(GuardEntity guard) {
        this.guard = guard;
    }

    public char getSendCallcenter() {
        return sendCallcenter;
    }

    public void setSendCallcenter(char sendCallcenter) {
        this.sendCallcenter = sendCallcenter;
    }
}
