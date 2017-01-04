package com.unidadcoronaria.prestaciones.data.entity;

public class MobileEntity extends BaseEntity{

	private Integer mobileId;
	private CompanyEntity company;
	private String name;
	private char active;
	private String alias;
	private MobileTypeEntity mobileType;

	public Integer getMobileId() {
		return mobileId;
	}

	public void setMobileId(Integer mobileId) {
		this.mobileId = mobileId;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public MobileTypeEntity getMobileType() {
		return mobileType;
	}

	public void setMobileType(MobileTypeEntity mobileType) {
		this.mobileType = mobileType;
	}
}
