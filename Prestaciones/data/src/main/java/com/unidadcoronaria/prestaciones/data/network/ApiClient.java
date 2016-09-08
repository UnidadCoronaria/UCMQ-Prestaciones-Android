package com.unidadcoronaria.prestaciones.data.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.ResultEntityCallback;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;
import com.unidadcoronaria.prestaciones.data.network.rest.MedicalServiceService;
import com.unidadcoronaria.prestaciones.data.network.rest.ResourceService;

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
    private static final String BASE_URL = "";
    private final Retrofit retrofit;
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
                    Request request = chain.request();

                    long t1 = System.nanoTime();
                    Log.d(getClass().getSimpleName(), String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

                    // TODO use IMEI
                    Request newRequest = chain.request().newBuilder()
                            .header("Authorization", "Bearer " + "IMEI")
                            .build();

                    final Response response = chain.proceed(newRequest);

                    long t2 = System.nanoTime();
                    Log.d(getClass().getSimpleName(), String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

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


    public void getMedicalServiceAttendedList(final SuccessFailureCallBack<List<MedicalServiceEntity>> callback) {
        retrofit.create(MedicalServiceService.class).getAttendedList().enqueue(new ResultEntityCallback<MedicalServiceEntity>(callback));
    }

    public void getMedicalServicePendingList(final SuccessFailureCallBack<List<MedicalServiceEntity>> callback) {
        retrofit.create(MedicalServiceService.class).getPendingList().enqueue(new ResultEntityCallback<MedicalServiceEntity>(callback));
    }

    //endregion
}