package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.network.response.MedicalServiceResponseEntity;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MedicalServiceService {

    @GET("match")
    Call<MedicalServiceResponseEntity> get(@Query("latitude") double latitude, @Query("longitude") double longitude);

    @FormUrlEncoded
    @POST("match")
    Call<MedicalServiceResponseEntity> post(@Field("imei") int imei);

}
