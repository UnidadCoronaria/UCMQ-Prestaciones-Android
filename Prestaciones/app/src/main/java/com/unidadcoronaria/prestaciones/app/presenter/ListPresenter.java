package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;
import android.util.Log;

import com.globallogic.recepcionvirtual.domain.usecase.ObtainGuestUseCase;

/**
 * Created by agustina.zimbello on 19/08/2016.
 */
public class ListPresenter {
    private ObtainGuestUseCase obtainGuestUseCase;
    private Context context;

    public ListPresenter(Context context) {
        obtainGuestUseCase = new ObtainGuestUseCase();
        this.context = context;
    }

    public void getGuest() {
        obtainGuestUseCase.execute(context);
        Log.d("onResume", "on resume");
    }


}
