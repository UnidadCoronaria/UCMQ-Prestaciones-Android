package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Cabin;
import com.unidadcoronaria.domain.model.MedicalServiceCallReason;
import com.unidadcoronaria.prestaciones.data.entity.CabinEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceCallReasonEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceCallReasonTransformer implements Transformer<MedicalServiceCallReasonEntity, MedicalServiceCallReason>, EntityTransformer<MedicalServiceCallReasonEntity, MedicalServiceCallReason> {

    private CallReasonTransformer callReasonTransformer = new CallReasonTransformer();

    @Override
    public MedicalServiceCallReason transform(MedicalServiceCallReasonEntity object) {
        MedicalServiceCallReason medicalServiceCallReason = new MedicalServiceCallReason();
        medicalServiceCallReason.setMedicalServiceCallReasonId(object.getMedicalServiceCallReasonId());
        if(object.getCallReasonMedicalService() != null) {
            medicalServiceCallReason.setCallReasonMedicalService(callReasonTransformer.transform(object.getCallReasonMedicalService()));
        }
        return medicalServiceCallReason;
    }

    @Override
    public List<MedicalServiceCallReason> transform(List<MedicalServiceCallReasonEntity> object) {
        List<MedicalServiceCallReason> mList = new ArrayList<>();
        for (MedicalServiceCallReasonEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public MedicalServiceCallReasonEntity transformToEntity(MedicalServiceCallReason object) {
        MedicalServiceCallReasonEntity medicalServiceCallReasonEntity = new MedicalServiceCallReasonEntity();
        medicalServiceCallReasonEntity.setMedicalServiceCallReasonId(object.getMedicalServiceCallReasonId());
        if(object.getCallReasonMedicalService() != null) {
            medicalServiceCallReasonEntity.setCallReasonMedicalService(callReasonTransformer.transformToEntity(object.getCallReasonMedicalService()));
        }
        return medicalServiceCallReasonEntity;
    }

    @Override
    public List<MedicalServiceCallReasonEntity> transformToEntity(List<MedicalServiceCallReason> object) {
        List<MedicalServiceCallReasonEntity> mList = new ArrayList<>();
        for (MedicalServiceCallReason entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
