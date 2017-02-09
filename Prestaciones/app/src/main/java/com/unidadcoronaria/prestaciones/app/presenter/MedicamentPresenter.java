package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.model.Diagnostic;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.domain.usecase.GetMedicamentUseCase;
import com.unidadcoronaria.domain.usecase.UpdateMedicalServiceUseCase;
import com.unidadcoronaria.domain.usecase.UpdateMedicalServiceWithMedicamentUseCase;
import com.unidadcoronaria.prestaciones.app.MedicamentView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicamentPresenter extends BasePresenter<MedicamentView> {

    private GetMedicamentUseCase mGetMedicamentUseCase;
    private UpdateMedicalServiceWithMedicamentUseCase medicalServiceWithMedicamentUseCase;
    private Context context;

    public MedicamentPresenter(MedicamentView view, Context context) {
        super(view);
        this.mGetMedicamentUseCase = new GetMedicamentUseCase();
        this.medicalServiceWithMedicamentUseCase = new UpdateMedicalServiceWithMedicamentUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetMedicamentUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetMedicamentUseCase.SuccessResponse response){
        view.onListMedicamentRetrieved(response.getMedicament());
        view.hideLoading();
    }

    public void update(List<Medicament> medicamentList, MedicalServiceResource medicalService, List<Diagnostic> diagnostics) {
        view.showLoading();
        medicalServiceWithMedicamentUseCase.setData(medicalService.getMedicalServiceResourceId(), medicamentList,diagnostics);
        medicalServiceWithMedicamentUseCase.execute(context);
    }

    @Subscribe
    public void onMedicalServiceUpdate(UpdateMedicalServiceWithMedicamentUseCase.SuccessResponse response){
        view.hideLoading();
        view.onUpdate();
    }

    @Subscribe
    public void onMedicalServiceUpdateError(UpdateMedicalServiceWithMedicamentUseCase.ErrorResponse response){
        view.hideLoading();
        view.onUpdateError();
    }
}
