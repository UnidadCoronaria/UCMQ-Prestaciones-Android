package com.unidadcoronaria.prestaciones.data.entity;

import java.util.Date;

public class MedicalServiceEntity {


	private Integer medicalServiceId;

	private Integer number;

	private String date;

	private String telephone;

	private MedicalServiceAddressEntity medicalServiceAddress;

	private String name;

	private String sex;

	private Integer age;

	private char copayment;

	private char copaymentPaid;

	private String status;

	private String observations;

	private String symptom;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public MedicalServiceAddressEntity getMedicalServiceAddress() {
		return medicalServiceAddress;
	}

	public void setMedicalServiceAddress(MedicalServiceAddressEntity medicalServiceAddress) {
		this.medicalServiceAddress = medicalServiceAddress;
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

	public MedicalServiceEntity() {
		super();
	}

	public MedicalServiceEntity(Integer medicalServiceId) {
		super();
		this.medicalServiceId = medicalServiceId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
}

