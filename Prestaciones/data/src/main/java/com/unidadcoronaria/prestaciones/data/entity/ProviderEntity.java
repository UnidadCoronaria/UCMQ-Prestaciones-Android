package com.unidadcoronaria.prestaciones.data.entity;

public class ProviderEntity {
	
	private Integer providerId;
	private String name;
	private Integer guardId;

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGuardId() {
		return guardId;
	}

	public void setGuardId(Integer guardId) {
		this.guardId = guardId;
	}

}
