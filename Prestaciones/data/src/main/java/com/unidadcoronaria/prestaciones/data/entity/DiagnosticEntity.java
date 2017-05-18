package com.unidadcoronaria.prestaciones.data.entity;

public class DiagnosticEntity extends BaseEntity	 {
	
	private Integer diagnosticId;
	private String name;
	private String description;
	private char active;
	private String number;

	public Integer getDiagnosticId() {
		return diagnosticId;
	}

	public void setDiagnosticId(Integer diagnosticId) {
		this.diagnosticId = diagnosticId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	
	
	
	
	
	

}
