package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;
import com.unidadcoronaria.prestaciones.data.entity.WatchItemEntity;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface WatchService {

    @POST("watch")
    Call<WatchEntity> post(@Body WatchEntity watchEntity);

    @GET("watch")
    Call<WatchEntity> get();

}
