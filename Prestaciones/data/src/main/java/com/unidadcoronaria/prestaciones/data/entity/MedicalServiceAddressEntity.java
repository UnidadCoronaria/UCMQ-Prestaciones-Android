package com.unidadcoronaria.prestaciones.data.entity;

public class MedicalServiceAddressEntity {
	
	private Integer medicalServiceAddressId;
	private String street;

	public Integer getMedicalServiceAddressId() {
		return medicalServiceAddressId;
	}

	public void setMedicalServiceAddressId(Integer medicalServiceAddressId) {
		this.medicalServiceAddressId = medicalServiceAddressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
}
