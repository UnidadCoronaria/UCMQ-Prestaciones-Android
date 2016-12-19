package com.unidadcoronaria.domain.transformer;

import android.util.Log;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceTransformer implements Transformer<MedicalServiceEntity,MedicalService> , EntityTransformer<MedicalServiceEntity, MedicalService>{

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

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
        try {
            medicalService.setDate(sdf.parse(object.getDate()));
        } catch (ParseException e) {
            Log.e(getClass().getName(), "Error parsing date in transform");
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

    @Override
    public MedicalServiceEntity transformToEntity(MedicalService object) {
        MedicalServiceEntity medicalServiceEntity = new MedicalServiceEntity();
        medicalServiceEntity.setName(object.getName());
        medicalServiceEntity.setMedicalServiceAddress(addressTransformer.transformToEntity(object.getMedicalServiceAddress()));
        medicalServiceEntity.setAge(object.getAge());
        medicalServiceEntity.setCopayment(object.getCopayment());
        medicalServiceEntity.setCopaymentPaid(object.getCopaymentPaid());
        medicalServiceEntity.setDate(sdf.format(object.getDate()));
        medicalServiceEntity.setMedicalServiceId(object.getMedicalServiceId());
        medicalServiceEntity.setNumber(object.getNumber());
        medicalServiceEntity.setSex(object.getSex());
        medicalServiceEntity.setStatus(object.getStatus());
        medicalServiceEntity.setTelephone(object.getTelephone());
        medicalServiceEntity.setObservations(object.getObservations());
        medicalServiceEntity.setSymptom(object.getSymptom());
        return medicalServiceEntity;
    }

    @Override
    public List<MedicalServiceEntity> transformToEntity(List<MedicalService> object) {
        List<MedicalServiceEntity> list = new ArrayList<>();
        for (MedicalService entity : object ) {
            list.add(transformToEntity(entity));
        }
        return list;
    }
}
