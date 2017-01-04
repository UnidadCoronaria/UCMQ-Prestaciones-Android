package com.unidadcoronaria.domain.model;

import java.util.Date;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class DeviceMessage extends BaseModel {

    private Integer deviceMessageId;
    private Date dateTime;
    private String message;
    private Device device;
    private Guard guard;
    private char sendCallcenter;

    public DeviceMessage(String message, Date dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public DeviceMessage() {
    }

    public Integer getDeviceMessageId() {
        return deviceMessageId;
    }

    public void setDeviceMessageId(Integer deviceMessageId) {
        this.deviceMessageId = deviceMessageId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Guard getGuard() {
        return guard;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public char getSendCallcenter() {
        return sendCallcenter;
    }

    public void setSendCallcenter(char sendCallcenter) {
        this.sendCallcenter = sendCallcenter;
    }
}
