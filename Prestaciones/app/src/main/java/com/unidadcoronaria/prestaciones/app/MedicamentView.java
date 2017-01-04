package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.Medicament;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MedicamentView extends View{

    void onListMedicamentRetrieved(List<Medicament> medicamentList);
}
