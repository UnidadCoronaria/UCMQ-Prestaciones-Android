package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.AddressMedicalService;
import com.unidadcoronaria.domain.model.Territory;
import com.unidadcoronaria.prestaciones.data.entity.AddressMedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class AddressMedicalServiceTransformer implements Transformer<AddressMedicalServiceEntity,AddressMedicalService>, EntityTransformer<AddressMedicalServiceEntity,AddressMedicalService> {

    private TerritoryTransformer territoryTransformer = new TerritoryTransformer();

    @Override
    public List<AddressMedicalService> transform(List<AddressMedicalServiceEntity> object) {
        List<AddressMedicalService> list = new ArrayList<AddressMedicalService>();
        for (AddressMedicalServiceEntity entity : object ) {
            list.add(transform(entity));
        }
        return list;
    }

    @Override
    public AddressMedicalService transform(AddressMedicalServiceEntity object) {
        AddressMedicalService addressMedicalService =  new AddressMedicalService();
        addressMedicalService.setAddressMedicalServiceId(object.getAddressMedicalServiceId());
        addressMedicalService.setStreet(object.getStreet());
        addressMedicalService.setNumber(object.getNumber());
        addressMedicalService.setStreet1(object.getStreet1());
        addressMedicalService.setStreet2(object.getStreet2());
        addressMedicalService.setLatitude(object.getLatitude());
        addressMedicalService.setLongitude(object.getLongitude());
        addressMedicalService.setInformation(object.getInformation());
        if(object.getTerritory() != null){
            addressMedicalService.setTerritory(territoryTransformer.transform(object.getTerritory()));
        }
        return addressMedicalService;
    }

    @Override
    public AddressMedicalServiceEntity transformToEntity(AddressMedicalService object) {
        AddressMedicalServiceEntity addressMedicalService =  new AddressMedicalServiceEntity();
        addressMedicalService.setAddressMedicalServiceId(object.getAddressMedicalServiceId());
        addressMedicalService.setStreet(object.getStreet());
        addressMedicalService.setNumber(object.getNumber());
        addressMedicalService.setStreet1(object.getStreet1());
        addressMedicalService.setStreet2(object.getStreet2());
        addressMedicalService.setLatitude(object.getLatitude());
        addressMedicalService.setLongitude(object.getLongitude());
        addressMedicalService.setInformation(object.getInformation());
        if(object.getTerritory() != null){
            addressMedicalService.setTerritory(territoryTransformer.transformToEntity(object.getTerritory()));
        }
        return addressMedicalService;
    }

    @Override
    public List<AddressMedicalServiceEntity> transformToEntity(List<AddressMedicalService> object) {
        List<AddressMedicalServiceEntity> list = new ArrayList<>();
        for (AddressMedicalService entity : object ) {
            list.add(transformToEntity(entity));
        }
        return list;
    }
}
