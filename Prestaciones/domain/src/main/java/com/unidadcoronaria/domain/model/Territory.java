package com.unidadcoronaria.domain.model;

import java.io.Serializable;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class Territory extends BaseModel implements Serializable{

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
