package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.usecase.GetSupplyUseCase;
import com.unidadcoronaria.domain.usecase.InitWatchUseCase;
import com.unidadcoronaria.prestaciones.app.viewpager.WatchView;

import org.greenrobot.eventbus.Subscribe;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class WatchPresenter extends BasePresenter<WatchView> {

    private InitWatchUseCase mInitWatchUseCase;
    private Context context;

    public WatchPresenter(WatchView view, Context context) {
        super(view);
        this.mInitWatchUseCase = new InitWatchUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mInitWatchUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetSupplyUseCase.SuccessResponse response){
        view.hideLoading();
    }

}
