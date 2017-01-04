package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MedicalServiceMedicament;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceMedicamentEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceMedicamentTransformer implements Transformer<MedicalServiceMedicamentEntity,MedicalServiceMedicament> , EntityTransformer<MedicalServiceMedicamentEntity, MedicalServiceMedicament>{

    private MedicalServiceResourceTransformer medicalServiceResourceTransformer = new MedicalServiceResourceTransformer();
    private MedicamentTransformer medicamentTransformer = new MedicamentTransformer();

    @Override
    public List<MedicalServiceMedicament> transform(List<MedicalServiceMedicamentEntity> object) {
        List<MedicalServiceMedicament> list = new ArrayList<>();
        for (MedicalServiceMedicamentEntity entity : object ) {
            list.add(transform(entity));
        }
        return list;
    }

    @Override
    public MedicalServiceMedicament transform(MedicalServiceMedicamentEntity object) {
        MedicalServiceMedicament medicalServiceMedicament = new MedicalServiceMedicament();
        medicalServiceMedicament.setMedicalServiceMedicamentId(object.getMedicalServiceMedicamentId());
        medicalServiceMedicament.setAmount(object.getAmount());
        if(object.getMedicalServiceResource() != null){
            medicalServiceMedicament.setMedicalServiceResource(medicalServiceResourceTransformer.transform(object.getMedicalServiceResource()));
        }
        if(object.getMedicament() != null){
            medicalServiceMedicament.setMedicament(medicamentTransformer.transform(object.getMedicament()));
        }
        return medicalServiceMedicament;
    }

    @Override
    public MedicalServiceMedicamentEntity transformToEntity(MedicalServiceMedicament object) {
        MedicalServiceMedicamentEntity medicalServiceMedicament = new MedicalServiceMedicamentEntity();
        medicalServiceMedicament.setMedicalServiceMedicamentId(object.getMedicalServiceMedicamentId());
        medicalServiceMedicament.setAmount(object.getAmount());
        if(object.getMedicalServiceResource() != null){
            medicalServiceMedicament.setMedicalServiceResource(medicalServiceResourceTransformer.transformToEntity(object.getMedicalServiceResource()));
        }
        if(object.getMedicament() != null){
            medicalServiceMedicament.setMedicament(medicamentTransformer.transformToEntity(object.getMedicament()));
        }
        return medicalServiceMedicament;
    }

    @Override
    public List<MedicalServiceMedicamentEntity> transformToEntity(List<MedicalServiceMedicament> object) {
        List<MedicalServiceMedicamentEntity> list = new ArrayList<>();
        for (MedicalServiceMedicament entity : object ) {
            list.add(transformToEntity(entity));
        }
        return list;
    }
}
