package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.dto.MobileObservationDTO;
import com.unidadcoronaria.prestaciones.data.entity.GuardEntity;
import com.unidadcoronaria.prestaciones.data.entity.MobileObservationEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface GuardService {

    @POST("mobileObservation/{guardId}")
    Call<Void> post(@Path("guardId") Integer guardId, @Body List<MobileObservationDTO> mobileObservationList);

}
