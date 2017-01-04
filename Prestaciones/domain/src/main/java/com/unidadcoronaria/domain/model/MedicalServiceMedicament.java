package com.unidadcoronaria.domain.model;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class MedicalServiceMedicament extends BaseModel {

    private Integer medicalServiceMedicamentId;
    private MedicalServiceResource medicalServiceResource;
    private Medicament medicament;
    private Double amount;

    public Integer getMedicalServiceMedicamentId() {
        return medicalServiceMedicamentId;
    }

    public void setMedicalServiceMedicamentId(Integer medicalServiceMedicamentId) {
        this.medicalServiceMedicamentId = medicalServiceMedicamentId;
    }

    public MedicalServiceResource getMedicalServiceResource() {
        return medicalServiceResource;
    }

    public void setMedicalServiceResource(MedicalServiceResource medicalServiceResource) {
        this.medicalServiceResource = medicalServiceResource;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
