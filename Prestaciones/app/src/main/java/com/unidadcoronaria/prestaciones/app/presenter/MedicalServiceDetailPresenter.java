package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.usecase.GetMessageUseCase;
import com.unidadcoronaria.domain.usecase.GetRouteUseCase;
import com.unidadcoronaria.prestaciones.app.MedicalServiceDetailView;
import com.unidadcoronaria.prestaciones.app.MessageView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MedicalServiceDetailPresenter extends BasePresenter<MedicalServiceDetailView>  {

    private Context mContext;
    private GetRouteUseCase mGetRouteUseCase;

    public MedicalServiceDetailPresenter(MedicalServiceDetailView view, Context context) {
        super(view);
        this.mContext = context;
        this.mGetRouteUseCase = new GetRouteUseCase();
    }

    public void getRoute(LatLng origin, MedicalService medicalService){
        mGetRouteUseCase.setData(String.valueOf(origin.latitude)+","+String.valueOf(origin.longitude),
                String.valueOf(medicalService.getMedicalServiceAddress().getLatitude())+","+String.valueOf(medicalService.getMedicalServiceAddress().getLongitude()) );
        mGetRouteUseCase.execute(mContext);
    }

    @Subscribe
    public void onMessageListReceived(GetRouteUseCase.SuccessResponse response){
        view.drawDirections(response.getRoute());
    }
}
