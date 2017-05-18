package com.unidadcoronaria.prestaciones.data.dto;

public class MedicalServiceResourceDTO {
	
	private Integer medicalServiceResourceId;
	private Integer state;
	private Double latitude;
	private Double longitude;

	
	public Integer getMedicalServiceResourceId() {
		return medicalServiceResourceId;
	}

	public void setMedicalServiceResourceId(Integer medicalServiceResourceId) {
		this.medicalServiceResourceId = medicalServiceResourceId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
