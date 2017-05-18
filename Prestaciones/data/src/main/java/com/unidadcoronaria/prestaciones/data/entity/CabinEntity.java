package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class CabinEntity extends BaseEntity {

    private Integer cabinId;
    private CompanyEntity company;
    private String name;
    private char letter;

    public Integer getCabinId() {
        return cabinId;
    }

    public void setCabinId(Integer cabinId) {
        this.cabinId = cabinId;
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

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
