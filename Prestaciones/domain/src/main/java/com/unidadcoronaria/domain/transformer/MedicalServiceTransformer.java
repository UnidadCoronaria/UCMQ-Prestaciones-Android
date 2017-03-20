package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceTransformer implements Transformer<MedicalServiceEntity,MedicalService> , EntityTransformer<MedicalServiceEntity, MedicalService>{

    private final AddressMedicalServiceTransformer addressTransformer = new AddressMedicalServiceTransformer();

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
        medicalService.setMedicalServiceId(object.getMedicalServiceId());
        medicalService.setNumber(object.getNumber());
        medicalService.setDate(object.getDate());
        medicalService.setTelephone(object.getTelephone());
        medicalService.setAddressMedicalService(addressTransformer.transform(object.getAddressMedicalService()));
        medicalService.setName(object.getName());
        medicalService.setSex(object.getSex());
        medicalService.setAge(object.getAge());
        medicalService.setCopayment(object.getCopayment());
        medicalService.setCopaymentPaid(object.getCopaymentPaid());
        medicalService.setStatus(object.getStatus());
        medicalService.setCabinId(object.getCabinId());
        medicalService.setCopaymentAmount(object.getCopaymentAmount());
        medicalService.setInternmentProtocol(object.getInternmentProtocol());
        medicalService.setEcgProtocol(object.getEcgProtocol());
        medicalService.setColour(object.getColour());
        medicalService.setEcg(object.getEcg());
        return medicalService;
    }

    @Override
    public MedicalServiceEntity transformToEntity(MedicalService object) {
        MedicalServiceEntity medicalService = new MedicalServiceEntity();
        medicalService.setMedicalServiceId(object.getMedicalServiceId());
        medicalService.setNumber(object.getNumber());
        medicalService.setDate(object.getDate());
        medicalService.setTelephone(object.getTelephone());
        medicalService.setAddressMedicalService(addressTransformer.transformToEntity(object.getAddressMedicalService()));
        medicalService.setName(object.getName());
        medicalService.setSex(object.getSex());
        medicalService.setAge(object.getAge());
        medicalService.setCopayment(object.getCopayment());
        medicalService.setCopaymentPaid(object.getCopaymentPaid());
        medicalService.setStatus(object.getStatus());
        medicalService.setCabinId(object.getCabinId());
        medicalService.setEcg(object.getEcg());
        return medicalService;
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
