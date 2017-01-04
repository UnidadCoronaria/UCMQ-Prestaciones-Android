package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.TypeMobileObservationEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MobileObservationService {

    @GET("typeMobileObservation")
    Call<List<TypeMobileObservationEntity>> get();

}
