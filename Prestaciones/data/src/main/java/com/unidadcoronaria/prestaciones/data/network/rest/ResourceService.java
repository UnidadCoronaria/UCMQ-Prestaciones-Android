package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.directions.ResourceEntity;

import retrofit.Call;
import retrofit.http.GET;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface ResourceService {

    @GET("resource")
    Call<ResourceEntity> get();

}
