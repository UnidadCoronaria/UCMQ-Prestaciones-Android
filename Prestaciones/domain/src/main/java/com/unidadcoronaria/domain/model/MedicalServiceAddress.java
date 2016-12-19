package com.unidadcoronaria.domain.model;

import java.io.Serializable;

public class MedicalServiceAddress implements Serializable{
	
	private Integer medicalServiceAddressId;
	private String street;
	private Double latitude;
	private Double longitude;

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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
