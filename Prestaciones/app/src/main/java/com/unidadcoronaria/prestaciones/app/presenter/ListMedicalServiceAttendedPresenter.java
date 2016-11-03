package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceAddress;
import com.unidadcoronaria.domain.usecase.GetMedicalServiceAttendedListUseCase;
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
public class ListMedicalServiceAttendedPresenter extends BasePresenter<ListMedicalServiceView> {

    private GetMedicalServiceAttendedListUseCase mGetMedicalServiceAttendedListUseCase;
    private Context context;

    public ListMedicalServiceAttendedPresenter(ListMedicalServiceView view, Context context) {
        super(view);
        this.mGetMedicalServiceAttendedListUseCase = new GetMedicalServiceAttendedListUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetMedicalServiceAttendedListUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetMedicalServiceAttendedListUseCase.SuccessResponse response){
        view.onListRetrieved(response.getList());
        view.hideLoading();
    }

}
