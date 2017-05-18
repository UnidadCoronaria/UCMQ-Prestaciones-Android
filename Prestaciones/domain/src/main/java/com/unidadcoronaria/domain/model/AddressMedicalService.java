package com.unidadcoronaria.domain.model;

import java.io.Serializable;

public class AddressMedicalService extends BaseModel implements Serializable {

	private Integer addressMedicalServiceId;
	private String street;
	private Integer number;
	private String street1;
	private String street2;
	private Territory territory;
	private Double latitude;
	private Double longitude;
	private String information;


	public Integer getAddressMedicalServiceId() {
		return addressMedicalServiceId;
	}

	public void setAddressMedicalServiceId(Integer addressMedicalServiceId) {
		this.addressMedicalServiceId = addressMedicalServiceId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
