package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MedicalServiceDiagnostic;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceDiagnosticEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceDiagnosticTransformer implements Transformer<MedicalServiceDiagnosticEntity,MedicalServiceDiagnostic> , EntityTransformer<MedicalServiceDiagnosticEntity, MedicalServiceDiagnostic>{

    private MedicalServiceTransformer medicalServiceTransformer = new MedicalServiceTransformer();
    private DiagnosticTransformer diagnosticTransformer = new DiagnosticTransformer();

    @Override
    public List<MedicalServiceDiagnostic> transform(List<MedicalServiceDiagnosticEntity> object) {
        List<MedicalServiceDiagnostic> list = new ArrayList<MedicalServiceDiagnostic>();
        for (MedicalServiceDiagnosticEntity entity : object ) {
            list.add(transform(entity));
        }
        return list;
    }

    @Override
    public MedicalServiceDiagnostic transform(MedicalServiceDiagnosticEntity object) {
        MedicalServiceDiagnostic medicalServiceDiagnostic = new MedicalServiceDiagnostic();
        medicalServiceDiagnostic.setMedicalServiceDiagnosticId(object.getMedicalServiceDiagnosticId());
        if(object.getMedicalService() != null){
            medicalServiceDiagnostic.setMedicalService(medicalServiceTransformer.transform(object.getMedicalService()));
        }
        if(object.getDiagnostic() != null){
            medicalServiceDiagnostic.setDiagnostic(diagnosticTransformer.transform(object.getDiagnostic()));
        }
        return medicalServiceDiagnostic;
    }

    @Override
    public MedicalServiceDiagnosticEntity transformToEntity(MedicalServiceDiagnostic object) {
        MedicalServiceDiagnosticEntity medicalServiceDiagnostic = new MedicalServiceDiagnosticEntity();
        medicalServiceDiagnostic.setMedicalServiceDiagnosticId(object.getMedicalServiceDiagnosticId());
        if(object.getMedicalService() != null){
            medicalServiceDiagnostic.setMedicalService(medicalServiceTransformer.transformToEntity(object.getMedicalService()));
        }
        if(object.getDiagnostic() != null){
            medicalServiceDiagnostic.setDiagnostic(diagnosticTransformer.transformToEntity(object.getDiagnostic()));
        }
        return medicalServiceDiagnostic;
    }

    @Override
    public List<MedicalServiceDiagnosticEntity> transformToEntity(List<MedicalServiceDiagnostic> object) {
        List<MedicalServiceDiagnosticEntity> list = new ArrayList<>();
        for (MedicalServiceDiagnostic entity : object ) {
            list.add(transformToEntity(entity));
        }
        return list;
    }
}
