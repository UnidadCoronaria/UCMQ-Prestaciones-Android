package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MobileObservation;
import com.unidadcoronaria.prestaciones.data.entity.MobileObservationEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MobileObservationTransformer implements Transformer<MobileObservationEntity, MobileObservation>,EntityTransformer<MobileObservationEntity, MobileObservation> {

    private MobileTransformer mobileTransformer = new MobileTransformer();
    private GuardTransformer guardTransformer = new GuardTransformer();
    private TypeMobileObservationTransformer typeMobileObservationTransformer = new TypeMobileObservationTransformer();

    @Override
    public MobileObservation transform(MobileObservationEntity object) {
        MobileObservation mobileObservation = new MobileObservation();
        mobileObservation.setMobileObservationId(object.getMobileObservationId());
        mobileObservation.setObservation(object.getObservation());
        mobileObservation.setDate(object.getDate());
        mobileObservation.setDateTime(object.getDateTime());
        mobileObservation.setState(object.getState());
        if(object.getMobile() != null) {
            mobileObservation.setMobile(mobileTransformer.transform(object.getMobile()));
        }
        if(object.getGuard() != null) {
            mobileObservation.setGuard(guardTransformer.transform(object.getGuard()));
        }
        if(object.getTypeMobileObservation() != null) {
            mobileObservation.setTypeMobileObservation(typeMobileObservationTransformer.transform(object.getTypeMobileObservation()));
        }
        return mobileObservation ;
    }

    @Override
    public List<MobileObservation> transform(List<MobileObservationEntity> object) {
        List<MobileObservation> mList = new ArrayList<>();
        for (MobileObservationEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public MobileObservationEntity transformToEntity(MobileObservation object) {
        MobileObservationEntity mobileObservation = new MobileObservationEntity();
        mobileObservation.setMobileObservationId(object.getMobileObservationId());
        mobileObservation.setObservation(object.getObservation());
        mobileObservation.setDate(object.getDate());
        mobileObservation.setDateTime(object.getDateTime());
        mobileObservation.setState(object.getState());
        if(object.getMobile() != null) {
            mobileObservation.setMobile(mobileTransformer.transformToEntity(object.getMobile()));
        }
        if(object.getGuard() != null) {
            mobileObservation.setGuard(guardTransformer.transformToEntity(object.getGuard()));
        }
        if(object.getTypeMobileObservation() != null) {
            mobileObservation.setTypeMobileObservation(typeMobileObservationTransformer.transformToEntity(object.getTypeMobileObservation()));
        }
        return mobileObservation ;
    }

    @Override
    public List<MobileObservationEntity> transformToEntity(List<MobileObservation> object) {
        List<MobileObservationEntity> mList = new ArrayList<>();
        for (MobileObservation entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
