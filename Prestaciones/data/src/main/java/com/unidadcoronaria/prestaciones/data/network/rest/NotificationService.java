package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.dto.MobileObservationDTO;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface NotificationService {

    @PUT("resource/updateToken")
    Call<Void> put(@Header("TokenGCM") String fcmToken);

}
