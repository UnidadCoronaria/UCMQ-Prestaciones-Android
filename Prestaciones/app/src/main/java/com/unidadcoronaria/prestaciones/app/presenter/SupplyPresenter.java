package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.usecase.GetMedicalServiceAttendedListUseCase;
import com.unidadcoronaria.domain.usecase.GetSupplyUseCase;
import com.unidadcoronaria.prestaciones.app.ListMedicalServiceView;
import com.unidadcoronaria.prestaciones.app.SupplyView;

import org.greenrobot.eventbus.Subscribe;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SupplyPresenter extends BasePresenter<SupplyView> {

    private GetSupplyUseCase mGetSupplyUseCase;
    private Context context;

    public SupplyPresenter(SupplyView view, Context context) {
        super(view);
        this.mGetSupplyUseCase = new GetSupplyUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetSupplyUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetSupplyUseCase.SuccessResponse response){
        view.onListSupplyRetrieved(response.getSupply());
        view.hideLoading();
    }

}
