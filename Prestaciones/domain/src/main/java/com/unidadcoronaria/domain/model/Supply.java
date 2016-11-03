package com.unidadcoronaria.domain.model;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class Supply extends BaseModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
