package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 11/7/2016.
 */

public class WatchItemEntity extends BaseEntity {

    private String name;
    private String note;
    private Boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
