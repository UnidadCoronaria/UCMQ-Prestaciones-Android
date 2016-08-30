package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.unidadcoronaria.prestaciones.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Agustin.Bala
 * @since  0.0.1
 */
public class SplashFragment extends BaseFragment{

    //region Constants
    private static final int SPLASH_DURATION = 2 * 1000;
    //endregion

    //region Variables
    private int total = 0;
    @BindView(R.id.progress_bar_splash)
    protected ProgressBar progressBarSplash;

    //enregion

    //region Constructors implementations
    public static BaseFragment newInstance() {
        return new SplashFragment();
    }
    //endregion

    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        initSplash();
        return view;
    }

    private void initSplash(){
        progressBarSplash.setProgress(total);
        new CountDownTimer(SPLASH_DURATION, 20) {
            public void onTick(long millisUntilFinished) {
                total += 2;
                progressBarSplash.setProgress(total);
            }

            @Override
            public void onFinish() {
                total = 0;
            }
        }.start();
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_splash;
    }



}