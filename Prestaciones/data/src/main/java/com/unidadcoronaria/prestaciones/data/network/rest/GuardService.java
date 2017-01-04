package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.GuardEntity;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface GuardService {

    @POST("watch")
    Call<GuardEntity> post(@Body GuardEntity watchEntity);

    @GET("watch")
    Call<GuardEntity> get();

}
