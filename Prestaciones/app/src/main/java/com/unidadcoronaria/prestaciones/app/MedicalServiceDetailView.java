package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.directions.Route;

/**
 * Created by AGUSTIN.BALA on 12/17/2016.
 */

public interface MedicalServiceDetailView extends  View {

    void drawDirections(Route route);
}
