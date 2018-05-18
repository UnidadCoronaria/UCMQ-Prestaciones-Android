package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.dto.DeviceMessageDTO;
import com.unidadcoronaria.prestaciones.data.entity.DeviceMessageEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface DeviceMessageService {

    @GET("deviceMessage/{guardId}/messages")
    Call<List<DeviceMessageEntity>> get(@Path("guardId") Integer watchId);

    @POST("deviceMessage/send")
    Call<DeviceMessageEntity> send(@Body DeviceMessageDTO deviceMessageDTO);

}
