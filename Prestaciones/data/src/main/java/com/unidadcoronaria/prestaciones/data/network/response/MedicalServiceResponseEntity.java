package com.unidadcoronaria.prestaciones.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceResponseEntity {

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }
}