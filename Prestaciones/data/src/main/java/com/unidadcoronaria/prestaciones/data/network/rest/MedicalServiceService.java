package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.GET;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MedicalServiceService {

    @GET("medicalService")
    Call<MedicalServiceEntity> getList(@Field("imei") String imei);

}
