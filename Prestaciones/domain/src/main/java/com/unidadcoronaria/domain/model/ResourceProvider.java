package com.unidadcoronaria.domain.model;

public class ResourceProvider extends BaseModel{
	
	private Integer resourceProviderId;
	private Resource resource;
	private Integer providerId;
	private Guard guard;

	public Integer getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(Integer resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Guard getGuard() {
		return guard;
	}

	public void setGuard(Guard guard) {
		this.guard = guard;
	}
}
