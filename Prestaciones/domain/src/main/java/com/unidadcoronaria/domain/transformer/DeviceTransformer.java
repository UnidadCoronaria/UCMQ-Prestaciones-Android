package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Device;
import com.unidadcoronaria.prestaciones.data.entity.DeviceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class DeviceTransformer implements Transformer<DeviceEntity, Device>, EntityTransformer<DeviceEntity, Device> {

    private CompanyTransformer companyTransformer = new CompanyTransformer();
    @Override
    public Device transform(DeviceEntity object) {
        Device device = new Device();
        device.setDeviceId(object.getDeviceId());
        device.setName(object.getName());
        device.setType(object.getType());
        device.setImei(object.getImei());
        return device;
    }

    @Override
    public List<Device> transform(List<DeviceEntity> object) {
        List<Device> mList = new ArrayList<>();
        for (DeviceEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public DeviceEntity transformToEntity(Device object) {
        DeviceEntity device = new DeviceEntity();
        device.setDeviceId(object.getDeviceId());
        device.setName(object.getName());
        device.setType(object.getType());
        device.setImei(object.getImei());
        return device;
    }

    @Override
    public List<DeviceEntity> transformToEntity(List<Device> object) {
        List<DeviceEntity> mList = new ArrayList<>();
        for (Device entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
