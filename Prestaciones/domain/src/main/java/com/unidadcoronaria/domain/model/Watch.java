package com.unidadcoronaria.domain.model;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class Watch extends BaseModel {

    private Resource resource;
    private List<WatchItem> itemList;
    private String note;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<WatchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<WatchItem> itemList) {
        this.itemList = itemList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
