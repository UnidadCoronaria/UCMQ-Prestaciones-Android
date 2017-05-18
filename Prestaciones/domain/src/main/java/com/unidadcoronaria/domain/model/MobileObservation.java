package com.unidadcoronaria.domain.model;

import java.util.Date;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class MobileObservation extends BaseModel {

    private Integer mobileObservationId;
    private Mobile mobile;
    private TypeMobileObservation typeMobileObservation;
    private Guard guard;
    private String observation;
    private Date dateTime;
    private Date date;
    private Boolean state;

    public Integer getMobileObservationId() {
        return mobileObservationId;
    }

    public void setMobileObservationId(Integer mobileObservationId) {
        this.mobileObservationId = mobileObservationId;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public TypeMobileObservation getTypeMobileObservation() {
        return typeMobileObservation;
    }

    public void setTypeMobileObservation(TypeMobileObservation typeMobileObservation) {
        this.typeMobileObservation = typeMobileObservation;
    }

    public Guard getGuard() {
        return guard;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
