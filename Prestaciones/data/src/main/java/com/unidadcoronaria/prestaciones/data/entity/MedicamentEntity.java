package com.unidadcoronaria.prestaciones.data.entity;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicamentEntity extends BaseEntity {

    private Integer medicamentId;
    private String name;
    private String number;
    private char active;
    private String type;
    private Double ammount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(Integer medicamentId) {
        this.medicamentId = medicamentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmmount() {
        return ammount;
    }

    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }
}
