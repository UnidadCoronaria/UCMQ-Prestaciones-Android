package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.model.MobileObservation;
import com.unidadcoronaria.domain.usecase.GetTypeMobileObservationUseCase;
import com.unidadcoronaria.domain.usecase.InitGuardUseCase;
import com.unidadcoronaria.prestaciones.app.viewpager.GuardView;
import com.unidadcoronaria.prestaciones.util.SessionHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GuardPresenter extends BasePresenter<GuardView> {

    private GetTypeMobileObservationUseCase mGetTypeMobileObservationUseCase;
    private InitGuardUseCase mInitGuardUseCase;
    private Context context;

    public GuardPresenter(GuardView view, Context context) {
        super(view);
        this.mGetTypeMobileObservationUseCase = new GetTypeMobileObservationUseCase();
        this.mInitGuardUseCase = new InitGuardUseCase();
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

    @Subscribe
    public void onWatchError(GetTypeMobileObservationUseCase.ErrorResponse response){
        view.onWatchError();
        view.hideLoading();
    }

    public void initGuard(List<MobileObservation> mobileObservationList){
        view.showLoading();
        mInitGuardUseCase.setData(mobileObservationList, Integer.valueOf(SessionHelper.getGuardId()));
        mInitGuardUseCase.execute(context);
    }

    @Subscribe
    public void onInitGuard(InitGuardUseCase.SuccessResponse response){
        view.onGuardInit();
        view.hideLoading();
    }

    @Subscribe
    public void onErrorGuard(InitGuardUseCase.ErrorResponse response){
        view.onGuardInitError();
        view.hideLoading();
    }

}
