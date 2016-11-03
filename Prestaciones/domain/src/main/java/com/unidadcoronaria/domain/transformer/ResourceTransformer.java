package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ResourceTransformer implements Transformer<ResourceEntity, Resource> {

    private MobileTransformer mobileTransformer = new MobileTransformer();

    @Override
    public Resource transform(ResourceEntity object) {
        Resource resource = new Resource();
        resource.setResourceId(object.getResourceId());
        resource.setOutOfService(object.getOutOfService());
        resource.setMobile(mobileTransformer.transform(object.getMobile()));
        return resource;
    }

    @Override
    public List<Resource> transform(List<ResourceEntity> object) {
        List<Resource> mList = new ArrayList<>();
        for (ResourceEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
