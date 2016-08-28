package com.unidadcoronaria.prestaciones.app.presenter;

import android.support.annotation.CallSuper;

import com.globallogic.recepcionvirtual.app.View;
import com.globallogic.recepcionvirtual.domain.model.GeneralUseCaseError;
import com.globallogic.recepcionvirtual.util.BusProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class BasePresenter<V extends View> {

    protected final V view;
    private EventBus mBus;

    public BasePresenter(V view) {
        this.view = view;
    }

    protected EventBus getBus() {
        if (mBus == null) {
            mBus = BusProvider.getDefaultBus();
        }
        return mBus;
    }


    @CallSuper
    public void onStart() {
    }

    @CallSuper
    public void onResume() {
        if(!getBus().isRegistered(this)) {
            getBus().register(this);
        }
    }

    @CallSuper
    public void onPause() {
        getBus().unregister(this);
    }

    @CallSuper
    public void onStop() {
    }

    @Subscribe
    public void onError(GeneralUseCaseError error) {
        switch (error.getCode()) {
            case 200:
                handleBusinessError(error);
                break;
            default:
                defaultError(error);
                break;
        }
    }

    protected void defaultError(GeneralUseCaseError error) {
        view.hideLoading();
        view.displayError("ERROR - code = " + String.valueOf(error.getCode()) + " - message = " + error.getErrorMessage());
    }

    private void handleBusinessError(GeneralUseCaseError error) {
        view.hideLoading();
        view.displayError("ERROR - code = " + String.valueOf(error.getCode()) + " - message = " + error.getErrorMessage());
    }


}
