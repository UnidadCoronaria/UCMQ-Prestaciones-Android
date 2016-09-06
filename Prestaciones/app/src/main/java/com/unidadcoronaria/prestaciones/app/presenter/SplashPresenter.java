package com.unidadcoronaria.prestaciones.app.presenter;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;

import com.unidadcoronaria.prestaciones.app.SplashView;
import com.unidadcoronaria.prestaciones.app.activity.ListMedicalServiceActivity;

/**
 * @author Agustin.Bala
 * @since 3.0.0
 */
public class SplashPresenter extends BasePresenter<SplashView>{

    //region Constants
    private static final int SPLASH_DURATION = 2 * 1000;
    //endregion

    private int total = 0;

    public SplashPresenter(SplashView view) {
        super(view);
    }

    public void initSplash(){
        view.onProgressUpdate(total);
        new CountDownTimer(SPLASH_DURATION, 20) {
            public void onTick(long millisUntilFinished) {
                total += 2;
                view.onProgressUpdate(total);
            }

            @Override
            public void onFinish() {
                total = 0;
                view.getActivity().startActivity(ListMedicalServiceActivity.getStartIntent(view.getActivity()));
                view.getActivity().finish();
            }
        }.start();
    }
}
