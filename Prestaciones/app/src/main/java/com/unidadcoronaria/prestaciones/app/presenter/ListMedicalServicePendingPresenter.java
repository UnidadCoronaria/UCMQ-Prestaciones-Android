package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceAddress;
import com.unidadcoronaria.domain.usecase.GetMedicalServicePendingListUseCase;
import com.unidadcoronaria.prestaciones.app.ListMedicalServiceView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ListMedicalServicePendingPresenter extends BasePresenter<ListMedicalServiceView> {

    private GetMedicalServicePendingListUseCase mGetMedicalServicePendingListUseCase;
    private Context context;

    public ListMedicalServicePendingPresenter(ListMedicalServiceView view, Context context) {
        super(view);
        mGetMedicalServicePendingListUseCase = new GetMedicalServicePendingListUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetMedicalServicePendingListUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetMedicalServicePendingListUseCase.SuccessResponse response){
        view.onListRetrieved(response.getList());
        view.hideLoading();
    }
}
