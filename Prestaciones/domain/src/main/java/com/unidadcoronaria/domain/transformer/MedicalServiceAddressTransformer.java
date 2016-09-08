package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MedicalServiceAddress;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceAddressEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceAddressTransformer implements Transformer<MedicalServiceAddressEntity,MedicalServiceAddress> {

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
        return medicalServiceAddress;
    }
}
