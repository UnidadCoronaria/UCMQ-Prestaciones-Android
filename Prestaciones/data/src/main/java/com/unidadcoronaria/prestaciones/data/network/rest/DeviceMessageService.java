package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.dto.DeviceMessageDTO;
import com.unidadcoronaria.prestaciones.data.entity.DeviceMessageEntity;

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
public interface DeviceMessageService {

    @GET("/deviceMessage/{guardId}/messages")
    Call<List<DeviceMessageEntity>> get(@Path("guardId") Integer watchId);

    @POST("/deviceMessage/send")
    Call<DeviceMessageEntity> send(@Body DeviceMessageDTO deviceMessageDTO);

}
