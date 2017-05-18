package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class MedicalServiceMedicamentEntity extends BaseEntity {

    private Integer medicalServiceMedicamentId;
    private MedicalServiceResourceEntity medicalServiceResource;
    private MedicamentEntity medicament;
    private Double amount;

    public Integer getMedicalServiceMedicamentId() {
        return medicalServiceMedicamentId;
    }

    public void setMedicalServiceMedicamentId(Integer medicalServiceMedicamentId) {
        this.medicalServiceMedicamentId = medicalServiceMedicamentId;
    }

    public MedicalServiceResourceEntity getMedicalServiceResource() {
        return medicalServiceResource;
    }

    public void setMedicalServiceResource(MedicalServiceResourceEntity medicalServiceResource) {
        this.medicalServiceResource = medicalServiceResource;
    }

    public MedicamentEntity getMedicament() {
        return medicament;
    }

    public void setMedicament(MedicamentEntity medicament) {
        this.medicament = medicament;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
