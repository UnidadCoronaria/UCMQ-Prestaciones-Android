package com.unidadcoronaria.domain.model;

import java.io.Serializable;

public class MobileType extends BaseModel implements Serializable{
	
	private Integer mobileTypeId;
	private String name;
	private String alias;

	public Integer getMobileTypeId() {
		return mobileTypeId;
	}

	public void setMobileTypeId(Integer mobileTypeId) {
		this.mobileTypeId = mobileTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	

}
