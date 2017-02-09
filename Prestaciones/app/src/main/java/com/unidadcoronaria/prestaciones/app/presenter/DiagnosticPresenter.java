package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.domain.usecase.GetDiagnosticUseCase;
import com.unidadcoronaria.domain.usecase.GetMedicamentUseCase;
import com.unidadcoronaria.domain.usecase.UpdateMedicalServiceWithMedicamentUseCase;
import com.unidadcoronaria.prestaciones.app.DiagnosticView;
import com.unidadcoronaria.prestaciones.app.MedicamentView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class DiagnosticPresenter extends BasePresenter<DiagnosticView> {

    private GetDiagnosticUseCase mGetDiagnosticUseCase;
    private Context context;

    public DiagnosticPresenter(DiagnosticView view, Context context) {
        super(view);
        this.mGetDiagnosticUseCase = new GetDiagnosticUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetDiagnosticUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetDiagnosticUseCase.SuccessResponse response){
        view.onListDiagnosticRetrieved(response.getDiagnostics());
        view.hideLoading();
    }

}
