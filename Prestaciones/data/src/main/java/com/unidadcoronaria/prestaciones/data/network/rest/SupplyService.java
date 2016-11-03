package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.SupplyEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface SupplyService {

    @GET("supply")
    Call<List<SupplyEntity>> get();

}
