package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.directions.RouteResponseEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MapService {

    @GET("directions/json")
    Call<RouteResponseEntity> getRoute(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key, @Query("units") String units );

}
