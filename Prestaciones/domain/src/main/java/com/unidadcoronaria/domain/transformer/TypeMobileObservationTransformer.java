package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.TypeMobileObservation;
import com.unidadcoronaria.prestaciones.data.entity.TypeMobileObservationEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class TypeMobileObservationTransformer implements Transformer<TypeMobileObservationEntity, TypeMobileObservation>,EntityTransformer<TypeMobileObservationEntity, TypeMobileObservation> {


    @Override
    public TypeMobileObservation transform(TypeMobileObservationEntity object) {
        TypeMobileObservation typeMobileObservation = new TypeMobileObservation();
        typeMobileObservation.setTypeMobileObservationId(object.getTypeMobileObservationId());
        typeMobileObservation.setName(object.getName());
        return typeMobileObservation ;
    }

    @Override
    public List<TypeMobileObservation> transform(List<TypeMobileObservationEntity> object) {
        List<TypeMobileObservation> mList = new ArrayList<>();
        for (TypeMobileObservationEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public TypeMobileObservationEntity transformToEntity(TypeMobileObservation object) {
        TypeMobileObservationEntity typeMobileObservation = new TypeMobileObservationEntity();
        typeMobileObservation.setTypeMobileObservationId(object.getTypeMobileObservationId());
        typeMobileObservation.setName(object.getName());
        return typeMobileObservation ;
    }

    @Override
    public List<TypeMobileObservationEntity> transformToEntity(List<TypeMobileObservation> object) {
        List<TypeMobileObservationEntity> mList = new ArrayList<>();
        for (TypeMobileObservation entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
