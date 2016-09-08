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
        //mGetMedicalServicePendingListUseCase.execute(context);
        MedicalService ms1 =  new MedicalService();
        MedicalServiceAddress msa1 = new MedicalServiceAddress();
        msa1.setStreet("Avellaneda 151 - Solano");
        ms1.setMedicalServiceAddress(msa1);
        ms1.setName("Mareos");
        MedicalService ms2 =  new MedicalService();
        MedicalServiceAddress msa2 = new MedicalServiceAddress();
        msa2.setStreet("Sarmiento 414 - Quilmes");
        ms2.setMedicalServiceAddress(msa2);
        ms2.setName("Dolor de cabeza");
        view.onListRetrieved(Arrays.asList(ms1,ms2,ms1,ms2,ms1,ms2,ms1,ms2,ms1,ms2));
        view.hideLoading();
    }

    @Subscribe
    public void onListRetrieved(GetMedicalServicePendingListUseCase.SuccessResponse response){
        view.onListRetrieved(response.getList());
        view.hideLoading();
    }
}
