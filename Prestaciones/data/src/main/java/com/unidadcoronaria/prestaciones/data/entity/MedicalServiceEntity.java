package com.unidadcoronaria.prestaciones.data.entity;

public class MedicalServiceEntity extends BaseEntity {

    private Long id;
    private String name;
    private Long time;
    private String mode;
    private Boolean attended;

    public MedicalServiceEntity(Long id, String name, Long time, String mode, Boolean attended) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.mode = mode;
        this.attended = attended;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }
}
