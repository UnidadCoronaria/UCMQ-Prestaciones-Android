package com.unidadcoronaria.domain.transformer;


import com.unidadcoronaria.domain.model.ResourceProvider;
import com.unidadcoronaria.prestaciones.data.entity.ResourceProviderEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ResourceProviderTransformer implements Transformer<ResourceProviderEntity, ResourceProvider> {

    private ResourceTransformer resourceTransformer = new ResourceTransformer();
    private GuardTransformer guardTransformer = new GuardTransformer();

    @Override
    public ResourceProvider transform(ResourceProviderEntity object) {
        ResourceProvider resourceProvider = new ResourceProvider();
        resourceProvider.setResourceProviderId(object.getResourceProviderId());
        resourceProvider.setProviderId(object.getProviderId());
        if(object.getResource() != null){
            resourceProvider.setResource(resourceTransformer.transform(object.getResource()));
        }
        if(object.getGuard() != null){
            resourceProvider.setGuard(guardTransformer.transform(object.getGuard()));
        }
        return resourceProvider;
    }

    @Override
    public List<ResourceProvider> transform(List<ResourceProviderEntity> object) {
        List<ResourceProvider> mList = new ArrayList<>();
        for (ResourceProviderEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
