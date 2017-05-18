package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class GuardEntity extends BaseEntity {

    private Integer guardId;
    private Integer providerId;

    public Integer getGuardId() {
        return guardId;
    }

    public void setGuardId(Integer guardId) {
        this.guardId = guardId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
}
