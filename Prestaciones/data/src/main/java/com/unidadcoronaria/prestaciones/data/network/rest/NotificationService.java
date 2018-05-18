package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.dto.MobileObservationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PUT;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface NotificationService {

    @PUT("resource/updateToken")
    Call<Void> put(@Header("TokenGCM") String fcmToken);

}
