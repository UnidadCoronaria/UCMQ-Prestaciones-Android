package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.Resource;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MainDrawerView extends View {

    void onResourceRetrieved(Resource resource);
}
