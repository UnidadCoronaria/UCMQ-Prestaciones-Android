package com.unidadcoronaria.domain.model;

import java.io.Serializable;

public class MedicalServiceAddress implements Serializable{
	
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
