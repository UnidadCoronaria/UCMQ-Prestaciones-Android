package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class TerritoryEntity extends BaseEntity{

    private Integer territoryId;
    private String name;

    public Integer getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(Integer territoryId) {
        this.territoryId = territoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
