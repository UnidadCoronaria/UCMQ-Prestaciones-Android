package com.unidadcoronaria.prestaciones.data.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unidadcoronaria.prestaciones.data.dto.CloseMedicalServiceResourceDTO;
import com.unidadcoronaria.prestaciones.data.dto.DeviceMessageDTO;
import com.unidadcoronaria.prestaciones.data.dto.MedicalServiceMedicamentDTO;
import com.unidadcoronaria.prestaciones.data.dto.MedicalServiceResourceDTO;
import com.unidadcoronaria.prestaciones.data.dto.MobileObservationDTO;
import com.unidadcoronaria.prestaciones.data.entity.DeviceMessageEntity;
import com.unidadcoronaria.prestaciones.data.entity.DiagnosticEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicamentEntity;
import com.unidadcoronaria.prestaciones.data.entity.MobileObservationEntity;
import com.unidadcoronaria.prestaciones.data.entity.ProviderEntity;
import com.unidadcoronaria.prestaciones.data.entity.TypeMobileObservationEntity;
import com.unidadcoronaria.prestaciones.data.entity.directions.RouteResponseEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.BaseCallback;
import com.unidadcoronaria.prestaciones.data.network.callback.EmptyResultEntityCallback;
import com.unidadcoronaria.prestaciones.data.network.callback.ResultEntityCallback;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;
import com.unidadcoronaria.prestaciones.data.network.callback.UpdateMedicalSerivceCallback;
import com.unidadcoronaria.prestaciones.data.network.rest.DeviceMessageService;
import com.unidadcoronaria.prestaciones.data.network.rest.DiagnosticService;
import com.unidadcoronaria.prestaciones.data.network.rest.GuardService;
import com.unidadcoronaria.prestaciones.data.network.rest.MapService;
import com.unidadcoronaria.prestaciones.data.network.rest.MedicalServiceService;
import com.unidadcoronaria.prestaciones.data.network.rest.MedicamentService;
import com.unidadcoronaria.prestaciones.data.network.rest.MobileObservationService;
import com.unidadcoronaria.prestaciones.data.network.rest.NotificationService;
import com.unidadcoronaria.prestaciones.data.network.rest.ProviderService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ApiClient {

    //region Properties
    public static String IMEI = "";
    private static final ApiClient INSTANCE = new ApiClient();
    //private static final String BASE_URL = "http://private-da46b-unidadcoronaria.apiary-mock.com";
    private static final String BASE_URL = "http://ayudamedica.sygladns.com.ar:7777/";
    //private static final String BASE_URL = "http://pdc.ucmq.com:60628/api/";
    private final Retrofit retrofit;
    private final Retrofit retrofitGoogleDirections;
    //endregion

    //region Constructor
    private ApiClient() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(false)
        .addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                try {
                    Request newRequest = chain.request().newBuilder()
                            //  .header("Authorization", "352584062478703")
                            .header("Authorization", IMEI)
                            .build();
                    Log.d("Request ",newRequest.url().toString());
                    final Response response = chain.proceed(newRequest);
                    Log.d("Request response ",newRequest.url().toString());
                    if(newRequest.body() != null)
                        Log.d("Request response Body",newRequest.body().toString());

                    return response;
                }
                catch (Exception e) {
                    if (e.getMessage() != null) {
                        Log.e("Request Error", e.getMessage());
                    }
                    throw e;
                }
            }
        }).addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        OkHttpClient clientDirections =  new OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build();
        retrofitGoogleDirections = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientDirections)
                .build();
    }

    public static class ConnectionError {

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
            dto.setState(entity.getState());
            dto.setTypeMobileObservationId(entity.getTypeMobileObservation().getTypeMobileObservationId());
            dtoList.add(dto);
        }
        retrofit.create(GuardService.class).post(guardId,dtoList).enqueue(new EmptyResultEntityCallback<Void>(callback));
    }

    public void sendMessage(final SuccessFailureCallBack<DeviceMessageEntity> callback, Integer guardId, DeviceMessageEntity message) {
        DeviceMessageDTO dto = new DeviceMessageDTO();
        dto.setMessage(message.getMessage());
        dto.setGuardId(guardId);
        retrofit.create(DeviceMessageService.class).send(dto).enqueue(new ResultEntityCallback<DeviceMessageEntity>(callback));
    }


    public void closeMedicalServiceResource(final SuccessFailureCallBack<MedicalServiceResourceEntity> callback, Integer medicalServiceId, Integer state, Double lat, Double lng) {
        MedicalServiceResourceDTO medicalServiceResourceDTO = new MedicalServiceResourceDTO();
        medicalServiceResourceDTO.setMedicalServiceResourceId(medicalServiceId);
        medicalServiceResourceDTO.setState(state);
        medicalServiceResourceDTO.setLatitude(lat);
        medicalServiceResourceDTO.setLongitude(lng);
        retrofit.create(MedicalServiceService.class).put(medicalServiceResourceDTO).enqueue(new UpdateMedicalSerivceCallback<MedicalServiceResourceEntity>(callback));
    }

    public void closeMedicalServiceResource(final SuccessFailureCallBack<MedicalServiceResourceEntity> callback, Integer medicalServiceId, List<MedicamentEntity> listMedicamentEntities, List<DiagnosticEntity> diagnosticEntities, Double lat, Double lng, char ecg, char copaymentPaid) {
        List<MedicalServiceMedicamentDTO> medicamentList = new ArrayList<>();
        for (MedicamentEntity medicamentEntity: listMedicamentEntities) {
            MedicalServiceMedicamentDTO dto = new MedicalServiceMedicamentDTO();
            dto.setMedicalServiceResourceId(medicalServiceId);
            dto.setMedicamentId(medicamentEntity.getMedicamentId());
            dto.setAmount(medicamentEntity.getAmmount());
            medicamentList.add(dto);
        }
        List<Integer> diagnostics = new ArrayList<>();
        for (DiagnosticEntity diagnosticEntity: diagnosticEntities) {
            diagnostics.add(diagnosticEntity.getDiagnosticId());
        }
        MedicalServiceResourceDTO medicalServiceResourceDTO = new MedicalServiceResourceDTO();
        medicalServiceResourceDTO.setMedicalServiceResourceId(medicalServiceId);
        medicalServiceResourceDTO.setState(6);
        medicalServiceResourceDTO.setLatitude(lat);
        medicalServiceResourceDTO.setLongitude(lng);
        CloseMedicalServiceResourceDTO closeMedicalServiceResourceDTO = new CloseMedicalServiceResourceDTO();
        closeMedicalServiceResourceDTO.setListMedicalServiceMedicamentDTO(medicamentList);
        closeMedicalServiceResourceDTO.setMedicalServiceResourceDTO(medicalServiceResourceDTO);
        closeMedicalServiceResourceDTO.setListDiagnosticId(diagnostics);
        closeMedicalServiceResourceDTO.setEcg(ecg);
        closeMedicalServiceResourceDTO.setCopaymentPaid(copaymentPaid);
        retrofit.create(MedicalServiceService.class).close(closeMedicalServiceResourceDTO).enqueue(new UpdateMedicalSerivceCallback<MedicalServiceResourceEntity>(callback));
    }

    //region Pending check
    public void getMedicalService(Integer medicalServiceId, final SuccessFailureCallBack<MedicalServiceResourceEntity> callback) {
        retrofit.create(MedicalServiceService.class).getById(medicalServiceId).enqueue(new ResultEntityCallback<MedicalServiceResourceEntity>(callback));
    }

    public void getMedicament(final SuccessFailureCallBack<List<MedicamentEntity>> callback) {
        retrofit.create(MedicamentService.class).get().enqueue(new ResultEntityCallback<List<MedicamentEntity>>(callback));
    }

    public void getRoute(final SuccessFailureCallBack<RouteResponseEntity> callback, String origin, String destination) {
        //Coronaria
        //retrofitGoogleDirections.create(MapService.class).getRoute(origin, destination,"AIzaSyCWMv2vGwkkv85_6ZnrBdHloaUBBpats0Q", "metric").enqueue(new ResultEntityCallback<RouteResponseEntity>(callback));
        // Ayuda
        retrofitGoogleDirections.create(MapService.class).getRoute(origin, destination,"AIzaSyAZCfodSDC_AvVy_lfCPgfdAT8yTTe1BLc", "metric").enqueue(new ResultEntityCallback<RouteResponseEntity>(callback));
    }

    public void getDiagnostic(final SuccessFailureCallBack<List<DiagnosticEntity>> callback) {
        retrofit.create(DiagnosticService.class).get().enqueue(new ResultEntityCallback<List<DiagnosticEntity>>(callback));
    }

    public void updateFCMToken(final SuccessFailureCallBack<Void> callback, String fcmToken) {
        retrofit.create(NotificationService.class).put(fcmToken).enqueue(new BaseCallback<Void>(callback));
    }

    //endregion
    //endregion
}