package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.Provider;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public interface MainView extends View {

    void onProviderListRetrieved(List<Provider> providerList);
}
