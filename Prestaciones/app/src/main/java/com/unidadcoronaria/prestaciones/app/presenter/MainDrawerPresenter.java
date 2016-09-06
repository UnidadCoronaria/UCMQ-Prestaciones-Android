package com.unidadcoronaria.prestaciones.app.presenter;

import com.unidadcoronaria.domain.usecase.GetResourceUseCase;
import com.unidadcoronaria.prestaciones.app.MainDrawerView;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MainDrawerPresenter extends BasePresenter<MainDrawerView> {

    public MainDrawerPresenter(MainDrawerView view) {
        super(view);
    }

    public void getData(){
        new GetResourceUseCase().execute(view.getActivity());
    }

    @Subscribe
    public void onResourceRetrieved(GetResourceUseCase.SuccessResponse response) {
        view.onResourceRetrieved(response.getResource());
    }

}