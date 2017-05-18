package com.unidadcoronaria.prestaciones.data.dto;

public class MobileObservationDTO {

	private Integer typeMobileObservationId;
	private String observation;
	private Boolean state;

	public Integer getTypeMobileObservationId() {
		return typeMobileObservationId;
	}

	public void setTypeMobileObservationId(Integer typeMobileObservationId) {
		this.typeMobileObservationId = typeMobileObservationId;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}
