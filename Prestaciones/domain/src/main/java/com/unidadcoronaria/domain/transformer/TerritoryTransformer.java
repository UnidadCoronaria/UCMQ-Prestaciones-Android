package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Territory;
import com.unidadcoronaria.prestaciones.data.entity.TerritoryEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class TerritoryTransformer implements Transformer<TerritoryEntity, Territory>, EntityTransformer<TerritoryEntity, Territory> {


    @Override
    public Territory transform(TerritoryEntity object) {
        Territory territory = new Territory();
        territory.setTerritoryId(object.getTerritoryId());
        territory.setName(object.getName());
        return territory;
    }

    @Override
    public List<Territory> transform(List<TerritoryEntity> object) {
        List<Territory> mList = new ArrayList<>();
        for (TerritoryEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public TerritoryEntity transformToEntity(Territory object) {
        TerritoryEntity entity = new TerritoryEntity();
        entity.setTerritoryId(object.getTerritoryId());
        entity.setName(object.getName());
        return entity;
    }

    @Override
    public List<TerritoryEntity> transformToEntity(List<Territory> object) {
        List<TerritoryEntity> mList = new ArrayList<>();
        for (Territory entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
