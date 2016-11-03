package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;

import retrofit.Call;
import retrofit.http.POST;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface WatchService {

    @POST("watch")
    Call<WatchEntity> post();

}
