package com.unidadcoronaria.prestaciones.data.entity;

public class MedicalServiceEntity {


	private Integer medicalServiceId;
	private Integer number;
	private Long date;
	private String telephone;
	private AddressMedicalServiceEntity addressMedicalService;
	private String name;
	private String sex;
	private Integer age;
	private char copayment;
	private char copaymentPaid;
	private Integer copaymentAmount;
	private char internmentProtocol;
	private char ecgProtocol;
	private String colour;
	private String status;
	private Integer cabinId;
	private char ecg;
	private String note;

	public Integer getMedicalServiceId() {
		return medicalServiceId;
	}

	public void setMedicalServiceId(Integer medicalServiceId) {
		this.medicalServiceId = medicalServiceId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public AddressMedicalServiceEntity getAddressMedicalService() {
		return addressMedicalService;
	}

	public void setAddressMedicalService(AddressMedicalServiceEntity addressMedicalService) {
		this.addressMedicalService = addressMedicalService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public char getCopayment() {
		return copayment;
	}

	public void setCopayment(char copayment) {
		this.copayment = copayment;
	}

	public char getCopaymentPaid() {
		return copaymentPaid;
	}

	public void setCopaymentPaid(char copaymentPaid) {
		this.copaymentPaid = copaymentPaid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCabinId() {
		return cabinId;
	}

	public void setCabinId(Integer cabinId) {
		this.cabinId = cabinId;
	}

	public Integer getCopaymentAmount() {
		return copaymentAmount;
	}

	public void setCopaymentAmount(Integer copaymentAmount) {
		this.copaymentAmount = copaymentAmount;
	}

	public char getInternmentProtocol() {
		return internmentProtocol;
	}

	public void setInternmentProtocol(char internmentProtocol) {
		this.internmentProtocol = internmentProtocol;
	}

	public char getEcgProtocol() {
		return ecgProtocol;
	}

	public void setEcgProtocol(char ecgProtocol) {
		this.ecgProtocol = ecgProtocol;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public char getEcg() {
		return ecg;
	}

	public void setEcg(char ecg) {
		this.ecg = ecg;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}

