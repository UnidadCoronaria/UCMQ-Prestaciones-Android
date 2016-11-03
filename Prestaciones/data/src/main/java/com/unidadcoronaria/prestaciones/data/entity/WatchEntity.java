package com.unidadcoronaria.prestaciones.data.entity;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class WatchEntity extends BaseEntity {

    private ResourceEntity resource;
    private Boolean clean;
    private Boolean caseComplete;
    private Boolean oxygenFull;

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    public Boolean getClean() {
        return clean;
    }

    public void setClean(Boolean clean) {
        this.clean = clean;
    }

    public Boolean getCaseComplete() {
        return caseComplete;
    }

    public void setCaseComplete(Boolean caseComplete) {
        this.caseComplete = caseComplete;
    }

    public Boolean getOxygenFull() {
        return oxygenFull;
    }

    public void setOxygenFull(Boolean oxygenFull) {
        this.oxygenFull = oxygenFull;
    }
}
