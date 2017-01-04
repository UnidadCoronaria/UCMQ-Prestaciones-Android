package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.usecase.GetMedicamentUseCase;
import com.unidadcoronaria.prestaciones.app.MedicamentView;

import org.greenrobot.eventbus.Subscribe;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicamentPresenter extends BasePresenter<MedicamentView> {

    private GetMedicamentUseCase mGetMedicamentUseCase;
    private Context context;

    public MedicamentPresenter(MedicamentView view, Context context) {
        super(view);
        this.mGetMedicamentUseCase = new GetMedicamentUseCase();
        this.context = context;
    }

    public void getList() {
        view.showLoading();
        mGetMedicamentUseCase.execute(context);
    }

    @Subscribe
    public void onListRetrieved(GetMedicamentUseCase.SuccessResponse response){
        view.onListMedicamentRetrieved(response.getMedicament());
        view.hideLoading();
    }

}
