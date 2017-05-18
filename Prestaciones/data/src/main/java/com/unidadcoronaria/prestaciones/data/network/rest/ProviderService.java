package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.ProviderEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface ProviderService {

    @GET("provider")
    Call<List<ProviderEntity>> get();

}
