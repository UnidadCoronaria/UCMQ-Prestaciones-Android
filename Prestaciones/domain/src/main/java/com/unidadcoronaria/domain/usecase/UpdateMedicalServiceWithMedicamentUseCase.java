package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Diagnostic;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.domain.transformer.DiagnosticTransformer;
import com.unidadcoronaria.domain.transformer.MedicalServiceResourceTransformer;
import com.unidadcoronaria.domain.transformer.MedicamentTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class UpdateMedicalServiceWithMedicamentUseCase extends UseCase<MedicalServiceResource> {

    private final MedicalServiceResourceTransformer transformer = new MedicalServiceResourceTransformer();
    private final MedicamentTransformer medicamentTransformer= new MedicamentTransformer();
    private final DiagnosticTransformer diagnosticTransformer= new DiagnosticTransformer();
    private Integer medicalServiceId;
    private List<Medicament> medicamentList;
    private List<Diagnostic> diagnostics;
    private Double lng;
    private Double lat;
    private char ecg;
    private char copaymentPaid;


    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().closeMedicalServiceResource(new SuccessFailureCallBack<MedicalServiceResourceEntity>() {
            @Override
            public void onSuccess(MedicalServiceResourceEntity medicalServiceEntity) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(medicalServiceEntity)));
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        }, medicalServiceId, medicamentTransformer.transformToEntity(medicamentList), diagnosticTransformer.transformToEntity(diagnostics), lat, lng, ecg, copaymentPaid);
    }

    public void setData(Integer medicalServiceId, List<Medicament> medicamentList, List<Diagnostic> diagnostics, Double lat, Double lng, char ecg, char copaymentPaid){
        this.medicalServiceId = medicalServiceId;
        this.medicamentList = medicamentList;
        this.diagnostics = diagnostics;
        this.lat = lat;
        this.lng = lng;
        this.ecg = ecg;
        this.copaymentPaid = copaymentPaid;
    }

    //region Inner Classes
    public static class SuccessResponse {

        private MedicalServiceResource medicalService;

        public SuccessResponse(MedicalServiceResource medicalService) {
            this.medicalService = medicalService;
        }

        public MedicalServiceResource getMedicalService() {
            return medicalService;
        }
    }

    public static class ErrorResponse {

    }
        //endregion
}
