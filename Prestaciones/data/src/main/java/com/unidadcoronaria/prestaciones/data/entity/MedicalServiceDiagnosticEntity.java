package com.unidadcoronaria.prestaciones.data.entity;

public class MedicalServiceDiagnosticEntity {
	
	private Integer medicalServiceDiagnosticId;
	private MedicalServiceEntity medicalService;
	private DiagnosticEntity diagnostic;

	public Integer getMedicalServiceDiagnosticId() {
		return medicalServiceDiagnosticId;
	}

	public void setMedicalServiceDiagnosticId(Integer medicalServiceDiagnosticId) {
		this.medicalServiceDiagnosticId = medicalServiceDiagnosticId;
	}

	public MedicalServiceEntity getMedicalService() {
		return medicalService;
	}

	public void setMedicalService(MedicalServiceEntity medicalService) {
		this.medicalService = medicalService;
	}

	public DiagnosticEntity getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(DiagnosticEntity diagnostic) {
		this.diagnostic = diagnostic;
	}
}
