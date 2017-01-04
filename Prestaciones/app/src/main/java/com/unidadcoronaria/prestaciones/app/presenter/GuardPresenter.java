package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.usecase.GetTypeMobileObservationUseCase;
import com.unidadcoronaria.prestaciones.app.viewpager.GuardView;

import org.greenrobot.eventbus.Subscribe;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GuardPresenter extends BasePresenter<GuardView> {

    private GetTypeMobileObservationUseCase mGetTypeMobileObservationUseCase;
    private Context context;

    public GuardPresenter(GuardView view, Context context) {
        super(view);
        this.mGetTypeMobileObservationUseCase = new GetTypeMobileObservationUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetTypeMobileObservationUseCase.execute(context);
    }

    @Subscribe
    public void onMobileObservationRetrieved(GetTypeMobileObservationUseCase.SuccessResponse response){
        view.onMobileObservationRetrieved(response.getTypeMobileObservationList());
        view.hideLoading();
    }

}
