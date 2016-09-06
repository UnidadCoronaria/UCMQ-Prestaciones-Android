package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.SplashView;
import com.unidadcoronaria.prestaciones.app.presenter.SplashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Agustin.Bala
 * @since  0.0.1
 */
public class SplashFragment extends BaseFragment implements SplashView{


    //region Variables
    @BindView(R.id.progress_bar_splash)
    protected ProgressBar progressBarSplash;

    private SplashPresenter presenter;
    //endregion

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
        presenter = new SplashPresenter(this);
        presenter.initSplash();
        return view;
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onProgressUpdate(Integer progress) {
        progressBarSplash.setProgress(progress);
    }

    @Override
    public void displayError(String message) {
        //Not implemented
    }

    @Override
    public void showLoading() {
        //Not implemented
    }

    @Override
    public void hideLoading() {
        //Not implemented
    }

    @Override
    public void finish() {

    }
}