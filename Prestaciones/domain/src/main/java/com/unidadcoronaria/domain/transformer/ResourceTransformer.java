package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ResourceTransformer implements Transformer<ResourceEntity, Resource> {
    @Override
    public Resource transform(ResourceEntity object) {
        return new Resource();
    }
}
