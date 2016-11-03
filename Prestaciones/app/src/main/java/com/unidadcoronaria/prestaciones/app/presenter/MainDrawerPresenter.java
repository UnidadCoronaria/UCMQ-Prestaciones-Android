package com.unidadcoronaria.prestaciones.app.presenter;

import com.unidadcoronaria.domain.usecase.GetResourceUseCase;
import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.app.MainDrawerView;
import com.unidadcoronaria.prestaciones.app.activity.MedicalServiceListActivity;
import com.unidadcoronaria.prestaciones.app.activity.WatchActivity;
import com.unidadcoronaria.prestaciones.util.SharedPreferencesHelper;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MainDrawerPresenter extends BasePresenter<MainDrawerView> {

    private static final String KEY_RESOURCE_NAME = "KEY_RESOURCE_NAME";
    private static final String KEY_RESOURCE_COMPANY_NAME = "KEY_RESOURCE_COMPANY_NAME";

    public MainDrawerPresenter(MainDrawerView view) {
        super(view);
    }

    public void getData(){
        if("".equals(SharedPreferencesHelper.getString(App.getInstance(), KEY_RESOURCE_NAME))) {
            new GetResourceUseCase().execute(view.getActivity());
        } else {
            view.onResourceRetrieved(SharedPreferencesHelper.getString(App.getInstance(), KEY_RESOURCE_NAME),SharedPreferencesHelper.getString(App.getInstance(), KEY_RESOURCE_COMPANY_NAME));
        }
    }

    @Subscribe
    public void onResourceRetrieved(GetResourceUseCase.SuccessResponse response) {
        view.onResourceRetrieved(response.getResource().getMobile().getName(), response.getResource().getMobile().getCompany().getName());
        SharedPreferencesHelper.putString(App.getInstance(), KEY_RESOURCE_NAME, response.getResource().getMobile().getName());
        SharedPreferencesHelper.putString(App.getInstance(), KEY_RESOURCE_COMPANY_NAME, response.getResource().getMobile().getCompany().getName());
    }

    public void openMedicalServices() {
        view.getActivity().startActivity(MedicalServiceListActivity.getStartIntent(view.getActivity()));
        view.getActivity().finish();
    }

    public void openInitWatch(){
        view.getActivity().startActivity(WatchActivity.getStartIntent(view.getActivity()));
        view.getActivity().finish();
    }

}
