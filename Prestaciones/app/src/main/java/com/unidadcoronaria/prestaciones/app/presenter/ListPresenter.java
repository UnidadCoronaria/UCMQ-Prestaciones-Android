package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;
import android.util.Log;

import com.unidadcoronaria.domain.usecase.GetMedicalServiceListUseCase;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ListPresenter {

    private GetMedicalServiceListUseCase mGetMedicalServiceListUseCase;
    private Context context;

    public ListPresenter(Context context) {
        mGetMedicalServiceListUseCase = new GetMedicalServiceListUseCase();
        this.context = context;
    }

    public void getList() {
        mGetMedicalServiceListUseCase.execute(context);
    }


}
