package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.DiagnosticEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicamentEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface DiagnosticService {

    @GET("diagnostic")
    Call<List<DiagnosticEntity>> get();

}
