package com.unidadcoronaria.domain.model;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class WatchItem extends BaseModel{

    private String name;
    private Boolean status;
    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
