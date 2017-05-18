package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.Diagnostic;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface DiagnosticView extends View{

    void onListDiagnosticRetrieved(List<Diagnostic> diagnostics);

    void onListError();
}
