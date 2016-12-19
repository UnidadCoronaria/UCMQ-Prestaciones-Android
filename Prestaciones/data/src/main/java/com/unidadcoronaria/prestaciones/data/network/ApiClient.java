package com.unidadcoronaria.prestaciones.data.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.entity.MessageEntity;
import com.unidadcoronaria.prestaciones.data.entity.directions.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.entity.SupplyEntity;
import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;
import com.unidadcoronaria.prestaciones.data.entity.directions.RouteResponseEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.ResultEntityCallback;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;
import com.unidadcoronaria.prestaciones.data.network.rest.MapService;
import com.unidadcoronaria.prestaciones.data.network.rest.MedicalServiceService;
import com.unidadcoronaria.prestaciones.data.network.rest.MessageService;
import com.unidadcoronaria.prestaciones.data.network.rest.ResourceService;
import com.unidadcoronaria.prestaciones.data.network.rest.SupplyService;
import com.unidadcoronaria.prestaciones.data.network.rest.WatchService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ApiClient {

    //region Properties
    private static final ApiClient INSTANCE = new ApiClient();
    private static final String BASE_URL = "http://private-da46b-unidadcoronaria.apiary-mock.com";
    private final Retrofit retrofit;
    private final Retrofit retrofitGoogleDirections;
    //endregion

    //region Constructor
    private ApiClient() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(60, TimeUnit.SECONDS);
        client.setConnectTimeout(60, TimeUnit.SECONDS);
        client.setWriteTimeout(60, TimeUnit.SECONDS);
        client.networkInterceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                try {
                    Request newRequest = chain.request().newBuilder()
                            .header("Authorization", "Bearer " + "IMEI")
                            .build();

                    final Response response = chain.proceed(newRequest);
                    return response;
                } catch (Exception e) {
                    if (e.getMessage() != null) {
                        Log.e("Error", e.getMessage());
                    }
                    throw e;
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        OkHttpClient clientDirections = new OkHttpClient();
        client.setReadTimeout(60, TimeUnit.SECONDS);
        client.setConnectTimeout(60, TimeUnit.SECONDS);
        client.setWriteTimeout(60, TimeUnit.SECONDS);

        retrofitGoogleDirections = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientDirections)
                .build();
    }
    //endregion

    //region Public Static Implementation
    public static ApiClient getInstance() {
        return INSTANCE;
    }
    //endregion

    //region Public Implementation

    public void getResource(final SuccessFailureCallBack<ResourceEntity> callback) {
        retrofit.create(ResourceService.class).get().enqueue(new ResultEntityCallback<ResourceEntity>(callback));
    }

    public void getMedicalService(Long medicalServiceId, final SuccessFailureCallBack<MedicalServiceEntity> callback) {
        retrofit.create(MedicalServiceService.class).getById(medicalServiceId).enqueue(new ResultEntityCallback<MedicalServiceEntity>(callback));
    }

    public void getMedicalServiceAttendedList(final SuccessFailureCallBack<List<MedicalServiceEntity>> callback) {
        retrofit.create(MedicalServiceService.class).getAttendedList().enqueue(new ResultEntityCallback<List<MedicalServiceEntity>>(callback));
    }

    public void getMedicalServicePendingList(final SuccessFailureCallBack<List<MedicalServiceEntity>> callback) {
        retrofit.create(MedicalServiceService.class).getPendingList().enqueue(new ResultEntityCallback<List<MedicalServiceEntity>>(callback));
    }

    public void getSupply(final SuccessFailureCallBack<List<SupplyEntity>> callback) {
        retrofit.create(SupplyService.class).get().enqueue(new ResultEntityCallback<List<SupplyEntity>>(callback));
    }

    public void initWatch(WatchEntity watchEntity, final SuccessFailureCallBack<WatchEntity> callback) {
        retrofit.create(WatchService.class).post(watchEntity).enqueue(new ResultEntityCallback<WatchEntity>(callback));
    }

    public void updateMedicalService(MedicalServiceEntity medicalServiceEntity, final SuccessFailureCallBack<MedicalServiceEntity> callback) {
        retrofit.create(MedicalServiceService.class).post(medicalServiceEntity).enqueue(new ResultEntityCallback<MedicalServiceEntity>(callback));
    }

    public void getWatch(final SuccessFailureCallBack<WatchEntity> callback) {
        retrofit.create(WatchService.class).get().enqueue(new ResultEntityCallback<WatchEntity>(callback));
    }

    public void getMessage(final SuccessFailureCallBack<List<MessageEntity>> callback) {
        retrofit.create(MessageService.class).get().enqueue(new ResultEntityCallback<List<MessageEntity>>(callback));
    }

    public void getRoute(final SuccessFailureCallBack<RouteResponseEntity> callback, String origin, String destination) {
        retrofitGoogleDirections.create(MapService.class).getRoute(origin, destination,"AIzaSyCWMv2vGwkkv85_6ZnrBdHloaUBBpats0Q", "metric").enqueue(new ResultEntityCallback<RouteResponseEntity>(callback));
    }
    //endregion
}