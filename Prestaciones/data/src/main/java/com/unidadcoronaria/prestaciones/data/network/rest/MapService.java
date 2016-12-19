package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.directions.RouteResponseEntity;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MapService {

    @GET("directions/json")
    Call<RouteResponseEntity> getRoute(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key, @Query("units") String units );

}
