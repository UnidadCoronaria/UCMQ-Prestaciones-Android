package com.unidadcoronaria.prestaciones.data.dto;

public class MedicalServiceMedicamentDTO {

	private Integer medicalServiceResourceId;
	private Integer medicamentId;
	private Double amount;
	
	
	public Integer getMedicalServiceResourceId() {
		return medicalServiceResourceId;
	}

	public void setMedicalServiceResourceId(Integer medicalServiceResourceId) {
		this.medicalServiceResourceId = medicalServiceResourceId;
	}

	public Integer getMedicamentId() {
		return medicamentId;
	}

	public void setMedicamentId(Integer medicamentId) {
		this.medicamentId = medicamentId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

}
