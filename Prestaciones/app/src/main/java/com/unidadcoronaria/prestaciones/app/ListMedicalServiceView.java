package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.MedicalServiceResource;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface ListMedicalServiceView extends View {

    void onListRetrieved(List<MedicalServiceResource> list);
}
