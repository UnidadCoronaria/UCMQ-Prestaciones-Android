package com.unidadcoronaria.prestaciones.data.entity;

public class ResourceEntity extends BaseEntity {

	private Integer resourceId;
	private MobileTypeEntity mobile;
	private char outOfService;
	private DeviceEntity device;

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public MobileTypeEntity getMobile() {
		return mobile;
	}

	public void setMobile(MobileTypeEntity mobile) {
		this.mobile = mobile;
	}

	public char getOutOfService() {
		return outOfService;
	}

	public void setOutOfService(char outOfService) {
		this.outOfService = outOfService;
	}

	public DeviceEntity getDevice() {
		return device;
	}

	public void setDevice(DeviceEntity device) {
		this.device = device;
	}
}
