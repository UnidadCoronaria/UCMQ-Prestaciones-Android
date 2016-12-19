package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.model.Watch;
import com.unidadcoronaria.domain.usecase.GetSupplyUseCase;
import com.unidadcoronaria.domain.usecase.GetWatchUseCase;
import com.unidadcoronaria.domain.usecase.InitWatchUseCase;
import com.unidadcoronaria.prestaciones.app.viewpager.WatchView;

import org.greenrobot.eventbus.Subscribe;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class WatchPresenter extends BasePresenter<WatchView> {

    private GetWatchUseCase mGetWatchUseCase;
    private InitWatchUseCase mInitWatchUseCase;
    private Context context;

    public WatchPresenter(WatchView view, Context context) {
        super(view);
        this.mGetWatchUseCase = new GetWatchUseCase();
        this.mInitWatchUseCase = new InitWatchUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetWatchUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetWatchUseCase.SuccessResponse response){
        view.onWatchRetrieved(response.getWatch());
        view.hideLoading();
    }
    public void initWatch(Watch watch){
        mInitWatchUseCase.execute(context);
    }

}
