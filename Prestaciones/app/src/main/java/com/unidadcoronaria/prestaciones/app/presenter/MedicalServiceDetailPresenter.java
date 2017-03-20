package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.usecase.GetMedicalServiceUseCase;
import com.unidadcoronaria.domain.usecase.GetRouteUseCase;
import com.unidadcoronaria.domain.usecase.UpdateMedicalServiceUseCase;
import com.unidadcoronaria.prestaciones.app.MedicalServiceDetailView;
import com.unidadcoronaria.prestaciones.util.LocationHelper;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MedicalServiceDetailPresenter extends BasePresenter<MedicalServiceDetailView>  {

    private Context mContext;
    private GetRouteUseCase mGetRouteUseCase;
    private GetMedicalServiceUseCase mGetMedicalServiceUseCase;
    private UpdateMedicalServiceUseCase mUpdateMedicalServiceUseCase;

    public MedicalServiceDetailPresenter(MedicalServiceDetailView view, Context context) {
        super(view);
        this.mContext = context;
        this.mGetRouteUseCase = new GetRouteUseCase();
        this.mUpdateMedicalServiceUseCase = new UpdateMedicalServiceUseCase();
        this.mGetMedicalServiceUseCase = new GetMedicalServiceUseCase();
    }

    public void getMedicalServiceResource(Integer id){
        view.showLoading();
        mGetMedicalServiceUseCase.setData(id);
        mGetMedicalServiceUseCase.execute(mContext);
    }

    @Subscribe
    public void onMedicalServiceResourceReceived(GetMedicalServiceUseCase.SuccessResponse response){
        view.onMedicalServiceResourceRetrieved(response.getMedicalService());
        view.hideLoading();
    }


    @Subscribe
    public void onMedicalServiceResourceErrorReceived(GetMedicalServiceUseCase.ErrorResponse response){
        view.hideLoading();
        view.displayError("Error obteniendo la prestación.");
    }

    public void getRoute(LatLng origin, MedicalService medicalService){
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
        this.mUpdateMedicalServiceUseCase.setData(medicalServiceResource.getMedicalServiceResourceId(), state, Double.parseDouble(LocationHelper.getLatitude()), Double.parseDouble(LocationHelper.getLongitude()));
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
