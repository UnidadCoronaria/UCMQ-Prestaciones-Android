package com.unidadcoronaria.prestaciones.data.entity;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SupplyEntity extends BaseEntity {

    private String name;
    private String quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
