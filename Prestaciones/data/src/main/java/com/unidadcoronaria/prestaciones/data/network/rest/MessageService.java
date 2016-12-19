package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.entity.MessageEntity;
import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MessageService {

    @GET("message")
    Call<List<MessageEntity>> get();

}
