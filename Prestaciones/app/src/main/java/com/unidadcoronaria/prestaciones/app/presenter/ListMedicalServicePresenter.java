package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;
import android.util.Log;

import com.unidadcoronaria.domain.usecase.GetMedicalServiceListUseCase;
import com.unidadcoronaria.prestaciones.app.View;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ListMedicalServicePresenter extends BasePresenter<View> {

    private GetMedicalServiceListUseCase mGetMedicalServiceListUseCase;
    private Context context;

    public ListMedicalServicePresenter(View view, Context context) {
        super(view);
        mGetMedicalServiceListUseCase = new GetMedicalServiceListUseCase();
        this.context = context;
    }

    public void getList() {
        mGetMedicalServiceListUseCase.execute(context);
    }


}
