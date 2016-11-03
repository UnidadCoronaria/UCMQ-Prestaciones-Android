package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.GET;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MedicalServiceService {

    @GET("medicalService/pending")
    Call<List<MedicalServiceEntity>> getPendingList();

    @GET("medicalService/attended")
    Call<List<MedicalServiceEntity>> getAttendedList();

}
