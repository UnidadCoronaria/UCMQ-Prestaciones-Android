package com.unidadcoronaria.prestaciones.data.entity;

import com.unidadcoronaria.prestaciones.data.entity.directions.ResourceEntity;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class WatchEntity extends BaseEntity {

    private ResourceEntity resource;
    private List<WatchItemEntity> itemList;
    private String note;

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    public List<WatchItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<WatchItemEntity> itemList) {
        this.itemList = itemList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
