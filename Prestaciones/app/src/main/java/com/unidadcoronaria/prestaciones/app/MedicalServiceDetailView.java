package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.model.directions.Route;

/**
 * Created by AGUSTIN.BALA on 12/17/2016.
 */

public interface MedicalServiceDetailView extends  View {

    void drawDirections(Route route);

    void onMedicalServiceUpdate(MedicalServiceResource medicalServiceResource);

    void onMedicalServiceResourceRetrieved(MedicalServiceResource medicalService);
}
