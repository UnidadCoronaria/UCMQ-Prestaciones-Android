package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Guard;
import com.unidadcoronaria.prestaciones.data.entity.GuardEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GuardTransformer implements Transformer<GuardEntity, Guard>, EntityTransformer<GuardEntity, Guard> {


    public Guard transform(GuardEntity object) {
        Guard guard = new Guard();
        guard.setGuardId(object.getGuardId());
        guard.setProviderId(object.getProviderId());
        return guard;
    }

    @Override
    public List<Guard> transform(List<GuardEntity> object) {
        List<Guard> mList = new ArrayList<>();
        for (GuardEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public GuardEntity transformToEntity(Guard object) {
        GuardEntity guard = new GuardEntity();
        guard.setGuardId(object.getGuardId());
        guard.setProviderId(object.getProviderId());
        return guard;
    }

    @Override
    public List<GuardEntity> transformToEntity(List<Guard> object) {
        List<GuardEntity> mList = new ArrayList<>();
        for (Guard entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
