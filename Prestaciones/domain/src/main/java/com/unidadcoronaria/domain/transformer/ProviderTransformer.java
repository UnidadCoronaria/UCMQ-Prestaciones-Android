package com.unidadcoronaria.domain.transformer;


import com.unidadcoronaria.domain.model.Provider;
import com.unidadcoronaria.prestaciones.data.entity.ProviderEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ProviderTransformer implements Transformer<ProviderEntity, Provider> {

    @Override
    public Provider transform(ProviderEntity object) {
        Provider provider = new Provider();
        provider.setProviderId(object.getProviderId());
        provider.setName(object.getName());
        provider.setGuardId(object.getGuardId());
        return provider;
    }

    @Override
    public List<Provider> transform(List<ProviderEntity> object) {
        List<Provider> mList = new ArrayList<>();
        for (ProviderEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
