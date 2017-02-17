package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.usecase.GetRouteUseCase;
import com.unidadcoronaria.domain.usecase.UpdateMedicalServiceUseCase;
import com.unidadcoronaria.prestaciones.app.MedicalServiceDetailView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MedicalServiceDetailPresenter extends BasePresenter<MedicalServiceDetailView>  {

    private Context mContext;
    private GetRouteUseCase mGetRouteUseCase;
    private UpdateMedicalServiceUseCase mUpdateMedicalServiceUseCase;

    public MedicalServiceDetailPresenter(MedicalServiceDetailView view, Context context) {
        super(view);
        this.mContext = context;
        this.mGetRouteUseCase = new GetRouteUseCase();
        this.mUpdateMedicalServiceUseCase = new UpdateMedicalServiceUseCase();
    }

    public void getRoute(LatLng origin, MedicalService medicalService){
        view.showLoading();
        mGetRouteUseCase.setData(String.valueOf(origin.latitude)+","+String.valueOf(origin.longitude),
                String.valueOf(medicalService.getAddressMedicalService().getLatitude())+","+String.valueOf(medicalService.getAddressMedicalService().getLongitude()) );
        mGetRouteUseCase.execute(mContext);
    }

    @Subscribe
    public void onRouteReceived(GetRouteUseCase.SuccessResponse response){
        view.drawDirections(response.getRoute());
        view.hideLoading();
    }

    @Subscribe
    public void onRouteError(GetRouteUseCase.ErrorResponse response){
        view.hideLoading();
    }

    public void updateState(Integer state, MedicalServiceResource medicalServiceResource){
        view.showLoading();
        this.mUpdateMedicalServiceUseCase.setData(medicalServiceResource.getMedicalServiceResourceId(), state);
        this.mUpdateMedicalServiceUseCase.execute(mContext);
    }

    @Subscribe
    public void onMedicalServiceUpdate(UpdateMedicalServiceUseCase.SuccessResponse response){
        view.onMedicalServiceUpdate(response.getMedicalService());
        view.hideLoading();
    }
    @Subscribe
    public void onMedicalServiceUpdateError(UpdateMedicalServiceUseCase.ErrorResponse response){
        view.hideLoading();
        view.displayError("No se pudo actualizar el estado de la prestación. Intentelo más tarde.");
    }

}
