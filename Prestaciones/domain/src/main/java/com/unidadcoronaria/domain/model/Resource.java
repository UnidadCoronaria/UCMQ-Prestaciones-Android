package com.unidadcoronaria.domain.model;

public class Resource extends BaseModel{

	private Integer resourceId;
	private MobileType mobile;
	private char outOfService;
	private Device device;

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public MobileType getMobile() {
		return mobile;
	}

	public void setMobile(MobileType mobile) {
		this.mobile = mobile;
	}

	public char getOutOfService() {
		return outOfService;
	}

	public void setOutOfService(char outOfService) {
		this.outOfService = outOfService;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
}
