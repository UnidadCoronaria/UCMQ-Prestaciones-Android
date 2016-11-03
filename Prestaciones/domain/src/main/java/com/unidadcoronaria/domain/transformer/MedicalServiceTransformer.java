package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceAddress;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceAddressEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceTransformer implements Transformer<MedicalServiceEntity,MedicalService> {

    private final MedicalServiceAddressTransformer addressTransformer = new MedicalServiceAddressTransformer();

    @Override
    public List<MedicalService> transform(List<MedicalServiceEntity> object) {
        List<MedicalService> list = new ArrayList<MedicalService>();
        for (MedicalServiceEntity entity : object ) {
            list.add(transform(entity));
        }
        return list;
    }

    @Override
    public MedicalService transform(MedicalServiceEntity object) {
        MedicalService medicalService = new MedicalService();
        medicalService.setName(object.getName());
        medicalService.setMedicalServiceAddress(addressTransformer.transform(object.getMedicalServiceAddress()));
        medicalService.setAge(object.getAge());
        medicalService.setCopayment(object.getCopayment());
        medicalService.setCopaymentPaid(object.getCopaymentPaid());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        try {
            medicalService.setDate(sdf.parse(object.getDate()));
        } catch (ParseException e) {
        }
        medicalService.setMedicalServiceId(object.getMedicalServiceId());
        medicalService.setNumber(object.getNumber());
        medicalService.setSex(object.getSex());
        medicalService.setStatus(object.getStatus());
        medicalService.setTelephone(object.getTelephone());
        medicalService.setObservations(object.getObservations());
        medicalService.setSymptom(object.getSymptom());
        return medicalService;
    }
}
