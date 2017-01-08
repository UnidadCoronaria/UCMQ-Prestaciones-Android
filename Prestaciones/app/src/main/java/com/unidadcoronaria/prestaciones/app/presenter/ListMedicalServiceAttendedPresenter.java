package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;
import android.util.Log;

import com.unidadcoronaria.domain.usecase.GetMedicalServiceAttendedListUseCase;
import com.unidadcoronaria.prestaciones.app.ListMedicalServiceView;
import com.unidadcoronaria.prestaciones.app.activity.event.OnUserChange;
import com.unidadcoronaria.prestaciones.util.SessionHelper;

import org.greenrobot.eventbus.Subscribe;


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
        if(!SessionHelper.getGuardId().isEmpty()) {
            mGetMedicalServiceAttendedListUseCase.setData(Integer.valueOf(SessionHelper.getGuardId()));
            mGetMedicalServiceAttendedListUseCase.execute(context);
        }
    }

    @Subscribe
    public void onListRetrieved(GetMedicalServiceAttendedListUseCase.SuccessResponse response){
        view.onListRetrieved(response.getList());
        view.hideLoading();
    }

    @Subscribe
    public void onUserChange(OnUserChange response){
        Log.i("Attended Presenter", "On user change");
        getList();
    }


}
