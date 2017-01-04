package com.unidadcoronaria.domain.model;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class Guard extends BaseModel {

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
