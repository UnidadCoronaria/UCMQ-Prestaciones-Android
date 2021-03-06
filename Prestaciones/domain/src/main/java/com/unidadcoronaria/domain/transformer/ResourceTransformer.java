package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ResourceTransformer implements Transformer<ResourceEntity, Resource>, EntityTransformer<ResourceEntity, Resource> {

    private DeviceTransformer deviceTransformer = new DeviceTransformer();
    private MobileTypeTransformer mobileTypeTransformer = new MobileTypeTransformer();

    @Override
    public Resource transform(ResourceEntity object) {
        Resource resource = new Resource();
        resource.setResourceId(object.getResourceId());
        resource.setOutOfService(object.getOutOfService());
        if(object.getMobile() != null) {
            resource.setMobile(mobileTypeTransformer.transform(object.getMobile()));
        }
        if(object.getDevice() != null) {
            resource.setDevice(deviceTransformer.transform(object.getDevice()));
        }
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

    @Override
    public ResourceEntity transformToEntity(Resource object) {
        ResourceEntity resource = new ResourceEntity();
        resource.setResourceId(object.getResourceId());
        resource.setOutOfService(object.getOutOfService());
        if(object.getMobile() != null) {
            resource.setMobile(mobileTypeTransformer.transformToEntity(object.getMobile()));
        }
        if(object.getDevice() != null) {
            resource.setDevice(deviceTransformer.transformToEntity(object.getDevice()));
        }
        return resource;
    }

    @Override
    public List<ResourceEntity> transformToEntity(List<Resource> object) {
        List<ResourceEntity> mList = new ArrayList<>();
        for (Resource entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
