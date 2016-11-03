package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.Supply;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface SupplyView extends View{

    void onListSupplyRetrieved(List<Supply> supplyList);
}
