package com.unidadcoronaria.prestaciones.app.presenter;

import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.domain.usecase.GetResourceUseCase;
import com.unidadcoronaria.prestaciones.app.MainDrawerView;
import com.unidadcoronaria.prestaciones.app.activity.MedicalServiceListActivity;

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
        //new GetResourceUseCase().execute(view.getActivity());
        view.onResourceRetrieved(new Resource());
    }

    @Subscribe
    public void onResourceRetrieved(GetResourceUseCase.SuccessResponse response) {
        view.onResourceRetrieved(response.getResource());
    }

    public void openMedicalServices(){
        view.getActivity().startActivity(MedicalServiceListActivity.getStartIntent(view.getActivity()));
        view.getActivity().finish();
    }

}
