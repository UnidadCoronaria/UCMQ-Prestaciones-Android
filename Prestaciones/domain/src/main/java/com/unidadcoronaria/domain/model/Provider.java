package com.unidadcoronaria.domain.model;

public class Provider extends BaseModel{
	
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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Provider provider = (Provider) o;

		return providerId != null ? providerId.equals(provider.providerId) : provider.providerId == null;

	}

	@Override
	public int hashCode() {
		return providerId != null ? providerId.hashCode() : 0;
	}
}
