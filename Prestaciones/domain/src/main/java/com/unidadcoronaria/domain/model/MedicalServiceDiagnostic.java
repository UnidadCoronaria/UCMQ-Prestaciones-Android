package com.unidadcoronaria.domain.model;

public class MedicalServiceDiagnostic extends BaseModel {
	
	private Integer medicalServiceDiagnosticId;
	private MedicalService medicalService;
	private Diagnostic diagnostic;

	public Integer getMedicalServiceDiagnosticId() {
		return medicalServiceDiagnosticId;
	}

	public void setMedicalServiceDiagnosticId(Integer medicalServiceDiagnosticId) {
		this.medicalServiceDiagnosticId = medicalServiceDiagnosticId;
	}

	public MedicalService getMedicalService() {
		return medicalService;
	}

	public void setMedicalService(MedicalService medicalService) {
		this.medicalService = medicalService;
	}

	public Diagnostic getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
	}
}
