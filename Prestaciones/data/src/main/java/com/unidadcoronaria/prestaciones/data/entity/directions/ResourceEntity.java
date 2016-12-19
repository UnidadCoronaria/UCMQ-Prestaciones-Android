package com.unidadcoronaria.prestaciones.data.entity.directions;

import com.unidadcoronaria.prestaciones.data.entity.BaseEntity;
import com.unidadcoronaria.prestaciones.data.entity.MobileEntity;

public class ResourceEntity extends BaseEntity {
	
	private Integer resourceId;
	
	private MobileEntity mobile;
	
	private char outOfService;

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public MobileEntity getMobile() {
		return mobile;
	}

	public void setMobile(MobileEntity mobile) {
		this.mobile = mobile;
	}

	public char getOutOfService() {
		return outOfService;
	}

	public void setOutOfService(char outOfService) {
		this.outOfService = outOfService;
	}

	

	
	
	

}
