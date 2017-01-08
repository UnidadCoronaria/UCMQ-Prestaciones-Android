package com.unidadcoronaria.prestaciones.data.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.unidadcoronaria.prestaciones.data.dto.DeviceMessageDTO;
import com.unidadcoronaria.prestaciones.data.dto.MobileObservationDTO;
import com.unidadcoronaria.prestaciones.data.entity.GuardEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;
import com.unidadcoronaria.prestaciones.data.entity.DeviceMessageEntity;
import com.unidadcoronaria.prestaciones.data.entity.MobileObservationEntity;
import com.unidadcoronaria.prestaciones.data.entity.ProviderEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicamentEntity;
import com.unidadcoronaria.prestaciones.data.entity.TypeMobileObservationEntity;
import com.unidadcoronaria.prestaciones.data.entity.directions.RouteResponseEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.ResultEntityCallback;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;
import com.unidadcoronaria.prestaciones.data.network.rest.MapService;
import com.unidadcoronaria.prestaciones.data.network.rest.MedicalServiceService;
import com.unidadcoronaria.prestaciones.data.network.rest.DeviceMessageService;
import com.unidadcoronaria.prestaciones.data.network.rest.MobileObservationService;
import com.unidadcoronaria.prestaciones.data.network.rest.ProviderService;
import com.unidadcoronaria.prestaciones.data.network.rest.MedicamentService;
import com.unidadcoronaria.prestaciones.data.network.rest.GuardService;

import java.io.IOException;
import java.util.ArrayList;
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
    public static String IMEI;
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
                            //.header("Authorization", IMEI)
                            .header("Authorization", "451236200698230")
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

    public void getProvider(final SuccessFailureCallBack<List<ProviderEntity>> callback) {
        retrofit.create(ProviderService.class).get().enqueue(new ResultEntityCallback<List<ProviderEntity>>(callback));
    }

    public void getTypeMobileObservation(final SuccessFailureCallBack<List<TypeMobileObservationEntity>> callback) {
        retrofit.create(MobileObservationService.class).get().enqueue(new ResultEntityCallback<List<TypeMobileObservationEntity>>(callback));
    }

    public void getMessage(final SuccessFailureCallBack<List<DeviceMessageEntity>> callback, Integer watchId) {
        retrofit.create(DeviceMessageService.class).get(watchId).enqueue(new ResultEntityCallback<List<DeviceMessageEntity>>(callback));
    }

    public void getMedicalServiceAttendedList(final SuccessFailureCallBack<List<MedicalServiceResourceEntity>> callback, Integer guardId) {
        retrofit.create(MedicalServiceService.class).getAttendedList(guardId).enqueue(new ResultEntityCallback<List<MedicalServiceResourceEntity>>(callback));
    }

    public void getMedicalServicePendingList(final SuccessFailureCallBack<List<MedicalServiceResourceEntity>> callback) {
        retrofit.create(MedicalServiceService.class).getPendingList().enqueue(new ResultEntityCallback<List<MedicalServiceResourceEntity>>(callback));
    }

    public void initGuard(List<MobileObservationEntity> mobileObservationEntities, Integer guardId, final SuccessFailureCallBack<Void> callback) {
        List<MobileObservationDTO> dtoList = new ArrayList<>();
        for (MobileObservationEntity entity : mobileObservationEntities) {
            MobileObservationDTO dto = new MobileObservationDTO();
            dto.setObservation(entity.getObservation());
            dto.setTypeMobileObservationId(entity.getTypeMobileObservation().getTypeMobileObservationId());
            dtoList.add(dto);
        }
        retrofit.create(GuardService.class).post(guardId,dtoList).enqueue(new ResultEntityCallback<Void>(callback));
    }

    public void sendMessage(final SuccessFailureCallBack<DeviceMessageEntity> callback, Integer guardId, DeviceMessageEntity message) {
        DeviceMessageDTO dto = new DeviceMessageDTO();
        dto.setMessage(message.getMessage());
        retrofit.create(DeviceMessageService.class).send(guardId, dto).enqueue(new ResultEntityCallback<DeviceMessageEntity>(callback));
    }


    //region Pending check
    public void getMedicalService(Long medicalServiceId, final SuccessFailureCallBack<MedicalServiceResourceEntity> callback) {
        retrofit.create(MedicalServiceService.class).getById(medicalServiceId).enqueue(new ResultEntityCallback<MedicalServiceResourceEntity>(callback));
    }

    public void getMedicament(final SuccessFailureCallBack<List<MedicamentEntity>> callback) {
        retrofit.create(MedicamentService.class).get().enqueue(new ResultEntityCallback<List<MedicamentEntity>>(callback));
    }



    public void updateMedicalService(MedicalServiceResourceEntity medicalServiceEntity, final SuccessFailureCallBack<MedicalServiceResourceEntity> callback) {
        retrofit.create(MedicalServiceService.class).post(medicalServiceEntity).enqueue(new ResultEntityCallback<MedicalServiceResourceEntity>(callback));
    }


    public void getRoute(final SuccessFailureCallBack<RouteResponseEntity> callback, String origin, String destination) {
        retrofitGoogleDirections.create(MapService.class).getRoute(origin, destination,"AIzaSyCWMv2vGwkkv85_6ZnrBdHloaUBBpats0Q", "metric").enqueue(new ResultEntityCallback<RouteResponseEntity>(callback));
    }

    //endregion
    //endregion
}