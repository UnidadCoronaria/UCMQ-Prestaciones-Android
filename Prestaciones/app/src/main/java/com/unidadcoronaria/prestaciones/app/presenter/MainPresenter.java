package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.usecase.GetProviderUseCase;
import com.unidadcoronaria.domain.usecase.UpdateFCMTokenUseCase;
import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.app.MainView;
import com.unidadcoronaria.prestaciones.util.SharedPreferencesHelper;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MainPresenter extends BasePresenter<MainView>  {

    private Context mContext;
    private GetProviderUseCase mGetProviderUseCase;
    private UpdateFCMTokenUseCase mUpdateFCMTokenUseCase;

    public MainPresenter(MainView view, Context context) {
        super(view);
        this.mContext = context;
        this.mGetProviderUseCase = new GetProviderUseCase();
        this.mUpdateFCMTokenUseCase = new UpdateFCMTokenUseCase();
        if(!SharedPreferencesHelper.getString(App.getInstance(),"FCM_TOKEN").isEmpty()){
            mUpdateFCMTokenUseCase.setData(SharedPreferencesHelper.getString(App.getInstance(),"FCM_TOKEN"));
            mUpdateFCMTokenUseCase.execute(App.getInstance());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getProvider();
    }

    public void getProvider() {
        mGetProviderUseCase.execute(mContext);
    }

    @Subscribe
    public void onProviderRetrieved(GetProviderUseCase.SuccessResponse response){
        view.onProviderListRetrieved(response.getList());
    }
}
