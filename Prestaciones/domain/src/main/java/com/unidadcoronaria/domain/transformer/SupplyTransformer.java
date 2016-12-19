package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.prestaciones.data.entity.SupplyEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SupplyTransformer implements Transformer<SupplyEntity, Supply>, EntityTransformer<SupplyEntity, Supply> {


    @Override
    public Supply transform(SupplyEntity object) {
        Supply resource = new Supply();
        resource.setName(object.getName());
        resource.setQuantity(object.getQuantity());
        return resource;
    }

    @Override
    public List<Supply> transform(List<SupplyEntity> object) {
        List<Supply> mList = new ArrayList<>();
        for (SupplyEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public SupplyEntity transformToEntity(Supply object) {
        SupplyEntity supply = new SupplyEntity();
        supply.setName(object.getName());
        supply.setQuantity(object.getQuantity());
        return supply;
    }

    @Override
    public List<SupplyEntity> transformToEntity(List<Supply> object) {
        return null;
    }
}
