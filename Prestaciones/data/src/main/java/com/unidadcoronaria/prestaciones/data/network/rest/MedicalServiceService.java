package com.unidadcoronaria.prestaciones.data.network.rest;

import com.unidadcoronaria.prestaciones.data.dto.CloseMedicalServiceResourceDTO;
import com.unidadcoronaria.prestaciones.data.dto.MedicalServiceMedicamentDTO;
import com.unidadcoronaria.prestaciones.data.dto.MedicalServiceResourceDTO;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MedicalServiceService {

    @GET("medicalServiceResource/pending")
    Call<List<MedicalServiceResourceEntity>> getPendingList();

    @GET("medicalServiceResource/{guardId}/attended")
    Call<List<MedicalServiceResourceEntity>> getAttendedList(@Path("guardId") Integer guardId);

    @GET("medicalServiceResource/{medicalServiceResourceId}")
    Call<MedicalServiceResourceEntity> getById(@Path("medicalServiceResourceId") Integer medicalServiceId);

    @PUT("medicalServiceResource/setState")
    Call<MedicalServiceResourceEntity> put(@Body MedicalServiceResourceDTO medicalServiceResourceDTO);

    @PUT("medicalServiceResource/close")
    Call<MedicalServiceResourceEntity> close(@Body CloseMedicalServiceResourceDTO closeMedicalServiceResourceDTO);
}
