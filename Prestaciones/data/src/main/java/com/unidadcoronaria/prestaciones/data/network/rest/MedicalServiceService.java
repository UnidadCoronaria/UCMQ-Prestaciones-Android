package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MedicalServiceService {

    @GET("medicalService/pending")
    Call<List<MedicalServiceEntity>> getPendingList();

    @GET("medicalService/attended")
    Call<List<MedicalServiceEntity>> getAttendedList();

    @GET("medicalService/{medicalServiceId}")
    Call<MedicalServiceEntity> getById(@Path("medicalServiceId") Long medicalServiceId);

    @POST("medicalService")
    Call<MedicalServiceEntity> post(@Body MedicalServiceEntity medicalServiceEntity);

}
