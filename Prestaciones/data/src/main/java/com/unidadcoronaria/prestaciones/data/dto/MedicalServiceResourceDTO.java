package com.unidadcoronaria.prestaciones.data.dto;

public class MedicalServiceResourceDTO {
	
	private Integer medicalServiceResourceId;
	private Integer state;
	private Double latitude;
	private Double longitude;
	private char copaymentPaid;
	private char ecg;

	
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

	public char getCopaymentPaid() {
		return copaymentPaid;
	}

	public void setCopaymentPaid(char copaymentPaid) {
		this.copaymentPaid = copaymentPaid;
	}

	public char getEcg() {
		return ecg;
	}

	public void setEcg(char ecg) {
		this.ecg = ecg;
	}
}
