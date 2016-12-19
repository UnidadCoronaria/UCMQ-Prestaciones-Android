package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MedicalServiceAddress;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceAddressEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceAddressTransformer implements Transformer<MedicalServiceAddressEntity,MedicalServiceAddress>, EntityTransformer<MedicalServiceAddressEntity,MedicalServiceAddress> {

    @Override
    public List<MedicalServiceAddress> transform(List<MedicalServiceAddressEntity> object) {
        List<MedicalServiceAddress> list = new ArrayList<MedicalServiceAddress>();
        for (MedicalServiceAddressEntity entity : object ) {
            list.add(transform(entity));
        }
        return list;
    }

    @Override
    public MedicalServiceAddress transform(MedicalServiceAddressEntity object) {
        MedicalServiceAddress medicalServiceAddress =  new MedicalServiceAddress();
        medicalServiceAddress.setMedicalServiceAddressId(object.getMedicalServiceAddressId());
        medicalServiceAddress.setStreet(object.getStreet());
        medicalServiceAddress.setLatitude(object.getLatitude());
        medicalServiceAddress.setLongitude(object.getLongitude());
        return medicalServiceAddress;
    }

    @Override
    public MedicalServiceAddressEntity transformToEntity(MedicalServiceAddress object) {
        MedicalServiceAddressEntity medicalServiceAddress =  new MedicalServiceAddressEntity();
        medicalServiceAddress.setMedicalServiceAddressId(object.getMedicalServiceAddressId());
        medicalServiceAddress.setStreet(object.getStreet());
        return medicalServiceAddress;
    }

    @Override
    public List<MedicalServiceAddressEntity> transformToEntity(List<MedicalServiceAddress> object) {
        List<MedicalServiceAddressEntity> list = new ArrayList<>();
        for (MedicalServiceAddress entity : object ) {
            list.add(transformToEntity(entity));
        }
        return list;
    }
}
