package com.unidadcoronaria.prestaciones.data.entity;

import java.util.Date;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class MobileObservationEntity extends BaseEntity {

    private Integer mobileObservationId;
    private MobileEntity mobile;
    private TypeMobileObservationEntity typeMobileObservation;
    private GuardEntity guard;
    private String observation;
    private Date dateTime;
    private Date date;

    public Integer getMobileObservationId() {
        return mobileObservationId;
    }

    public void setMobileObservationId(Integer mobileObservationId) {
        this.mobileObservationId = mobileObservationId;
    }

    public MobileEntity getMobile() {
        return mobile;
    }

    public void setMobile(MobileEntity mobile) {
        this.mobile = mobile;
    }

    public TypeMobileObservationEntity getTypeMobileObservation() {
        return typeMobileObservation;
    }

    public void setTypeMobileObservation(TypeMobileObservationEntity typeMobileObservation) {
        this.typeMobileObservation = typeMobileObservation;
    }

    public GuardEntity getGuard() {
        return guard;
    }

    public void setGuard(GuardEntity guard) {
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
}
