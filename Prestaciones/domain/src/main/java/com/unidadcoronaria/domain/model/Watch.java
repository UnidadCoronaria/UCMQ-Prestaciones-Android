package com.unidadcoronaria.domain.model;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class Watch extends BaseModel {

    private Resource resource;
    private Boolean clean;
    private Boolean caseComplete;
    private Boolean oxygenFull;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
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
