package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Diagnostic;
import com.unidadcoronaria.prestaciones.data.entity.DiagnosticEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class DiagnosticTransformer implements Transformer<DiagnosticEntity, Diagnostic>, EntityTransformer<DiagnosticEntity, Diagnostic> {


    public Diagnostic transform(DiagnosticEntity object) {
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setDiagnosticId(object.getDiagnosticId());
        diagnostic.setName(object.getName());
        diagnostic.setDescription(object.getDescription());
        diagnostic.setActive(object.getActive());
        diagnostic.setNumber(object.getNumber());
        return diagnostic;
    }

    @Override
    public List<Diagnostic> transform(List<DiagnosticEntity> object) {
        List<Diagnostic> mList = new ArrayList<>();
        for (DiagnosticEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public DiagnosticEntity transformToEntity(Diagnostic object) {
        DiagnosticEntity diagnostic = new DiagnosticEntity();
        diagnostic.setDiagnosticId(object.getDiagnosticId());
        diagnostic.setName(object.getName());
        diagnostic.setDescription(object.getDescription());
        diagnostic.setActive(object.getActive());
        diagnostic.setNumber(object.getNumber());
        return diagnostic;
    }

    @Override
    public List<DiagnosticEntity> transformToEntity(List<Diagnostic> object) {
        List<DiagnosticEntity> mList = new ArrayList<>();
        for (Diagnostic entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
