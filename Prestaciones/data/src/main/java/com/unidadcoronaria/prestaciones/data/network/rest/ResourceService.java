package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;

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
public interface ResourceService {

    @GET("resource")
    Call<ResourceEntity> get();

}
