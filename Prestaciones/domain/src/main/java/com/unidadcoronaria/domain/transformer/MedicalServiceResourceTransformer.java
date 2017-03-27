package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceResourceTransformer implements Transformer<MedicalServiceResourceEntity, MedicalServiceResource>, EntityTransformer<MedicalServiceResourceEntity, MedicalServiceResource> {

    private MedicalServiceTransformer medicalServiceTransformer = new MedicalServiceTransformer();
    private ResourceTransformer resourceTransformer = new ResourceTransformer();
    private final MedicalServiceCallReasonTransformer medicalServiceCallReasonTransformer = new MedicalServiceCallReasonTransformer();


    @Override
    public MedicalServiceResourceEntity transformToEntity(MedicalServiceResource object) {
        MedicalServiceResourceEntity entity = new MedicalServiceResourceEntity();
        entity.setMedicalServiceResourceId(object.getMedicalServiceResourceId());
        if(object.getMedicalService() != null) {
            entity.setMedicalService(medicalServiceTransformer.transformToEntity(object.getMedicalService()));
        }
        if(object.getResource() != null){
            entity.setResource(resourceTransformer.transformToEntity(object.getResource()));
        }
        entity.setCanceled(object.getCanceled());
        entity.setClosedMedicalService(object.getClosedMedicalService());
        entity.setCurrentState(object.getCurrentState());
        if(object.getAuthorizedStates() != null){
            entity.setAuthorizedStates(object.getAuthorizedStates());
        }
        entity.setPlanDetail(object.getPlanName());
        if(object.getMedicalServiceCallReason() != null){
            entity.setMedicalServiceCallReason(medicalServiceCallReasonTransformer.transformToEntity(object.getMedicalServiceCallReason()));
        }
        return entity;
    }

    @Override
    public List<MedicalServiceResourceEntity> transformToEntity(List<MedicalServiceResource> object) {
        List<MedicalServiceResourceEntity> mList = new ArrayList<>();
        for (MedicalServiceResource entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }

    @Override
    public MedicalServiceResource transform(MedicalServiceResourceEntity object) {
        MedicalServiceResource entity = new MedicalServiceResource();
        entity.setMedicalServiceResourceId(object.getMedicalServiceResourceId());
        if(object.getMedicalService() != null) {
            entity.setMedicalService(medicalServiceTransformer.transform(object.getMedicalService()));
        }
        if(object.getResource() != null){
            entity.setResource(resourceTransformer.transform(object.getResource()));
        }
        entity.setCanceled(object.getCanceled());
        entity.setClosedMedicalService(object.getClosedMedicalService());
        entity.setCurrentState(object.getCurrentState());
        if(object.getAuthorizedStates() != null){
            entity.setAuthorizedStates(object.getAuthorizedStates());
        }
        entity.setPlanName(object.getPlanDetail());
        if(object.getMedicalServiceCallReason() != null){
            entity.setMedicalServiceCallReason(medicalServiceCallReasonTransformer.transform(object.getMedicalServiceCallReason()));
        }
        return entity;
    }

    @Override
    public List<MedicalServiceResource> transform(List<MedicalServiceResourceEntity> object) {
        List<MedicalServiceResource> mList = new ArrayList<>();
        for (MedicalServiceResourceEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
