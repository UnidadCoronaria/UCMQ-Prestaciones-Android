package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;
import android.util.Log;

import com.unidadcoronaria.domain.usecase.GetMedicalServicePendingListUseCase;
import com.unidadcoronaria.prestaciones.app.ListMedicalServiceView;
import com.unidadcoronaria.prestaciones.app.activity.event.OnUserChange;

import org.greenrobot.eventbus.Subscribe;


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

    @Subscribe
    public void onUserChange(OnUserChange response){
        Log.i("Pending", "On user change");
        getList();
    }
}
