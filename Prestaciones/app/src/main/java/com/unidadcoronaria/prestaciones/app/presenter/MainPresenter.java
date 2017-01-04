package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.usecase.GetProviderUseCase;
import com.unidadcoronaria.prestaciones.app.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MainPresenter extends BasePresenter<MainView>  {

    private Context mContext;
    private GetProviderUseCase mGetProviderUseCase;

    public MainPresenter(MainView view, Context context) {
        super(view);
        this.mContext = context;
        this.mGetProviderUseCase = new GetProviderUseCase();
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
