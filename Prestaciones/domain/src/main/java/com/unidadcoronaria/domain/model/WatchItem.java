package com.unidadcoronaria.domain.model;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class WatchItem extends BaseModel{

    private String name;
    private Boolean state;
    private String note;

    public WatchItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
