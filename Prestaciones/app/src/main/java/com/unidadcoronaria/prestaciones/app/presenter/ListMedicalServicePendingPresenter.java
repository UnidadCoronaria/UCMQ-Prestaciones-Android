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
        MedicalServiceAddress msa1 = new MedicalServiceAddress();
        msa1.setStreet("Avellaneda 151 - Solano");
        MedicalService ms1 =  new MedicalService(msa1, "Marta Sanchez","Femenino", 20,"No tiene timbre", "Dolores de cabeza");
        MedicalServiceAddress msa2 = new MedicalServiceAddress();
        msa2.setStreet("Sarmiento 414 - Quilmes");
        MedicalService ms2 =  new MedicalService(msa2, "Pedro Rodrigueza", "Masculino", 51, "Cuidado con el perro que es malo y te puede morder", "Artritis, dolor de cabeza, mareos");
        view.onListRetrieved(Arrays.asList(ms1,ms2));
        view.hideLoading();
    }

    @Subscribe
    public void onListRetrieved(GetMedicalServicePendingListUseCase.SuccessResponse response){
        view.onListRetrieved(response.getList());
        view.hideLoading();
    }
}
