package com.unidadcoronaria.prestaciones.data.entity;

public class MobileTypeEntity extends BaseEntity {

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
