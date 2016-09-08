package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.MedicalService;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface ListMedicalServiceView extends View {

    void onListRetrieved(List<MedicalService> list);
}
