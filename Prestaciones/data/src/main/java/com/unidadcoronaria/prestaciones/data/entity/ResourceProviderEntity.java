package com.unidadcoronaria.prestaciones.data.entity;

public class ResourceProviderEntity {
	
	private Integer resourceProviderId;
	private ResourceEntity resource;
	private Integer providerId;
	private GuardEntity guard;

	public Integer getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(Integer resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public ResourceEntity getResource() {
		return resource;
	}

	public void setResource(ResourceEntity resource) {
		this.resource = resource;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public GuardEntity getGuard() {
		return guard;
	}

	public void setGuard(GuardEntity guard) {
		this.guard = guard;
	}
}
