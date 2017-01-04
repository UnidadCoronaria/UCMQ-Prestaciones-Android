package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.DeviceMessage;
import com.unidadcoronaria.prestaciones.data.entity.DeviceEntity;
import com.unidadcoronaria.prestaciones.data.entity.DeviceMessageEntity;
import com.unidadcoronaria.prestaciones.data.entity.GuardEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class DeviceMessageTransformer implements Transformer<DeviceMessageEntity, DeviceMessage>, EntityTransformer<DeviceMessageEntity, DeviceMessage> {

    private DeviceTransformer deviceTransformer = new DeviceTransformer();
    private GuardTransformer guardTransformer = new GuardTransformer();

    @Override
    public DeviceMessage transform(DeviceMessageEntity object) {
        DeviceMessage deviceMessage = new DeviceMessage();
        deviceMessage.setDeviceMessageId(object.getDeviceMessageId());
        deviceMessage.setDateTime(object.getDateTime());
        deviceMessage.setMessage(object.getMessage());
        deviceMessage.setSendCallcenter(object.getSendCallcenter());
        if(object.getGuard() != null){
            deviceMessage.setGuard(guardTransformer.transform(object.getGuard()));
        }
        if(object.getDevice() != null){
            deviceMessage.setDevice(deviceTransformer.transform(object.getDevice()));
        }
        return deviceMessage;
    }

    @Override
    public List<DeviceMessage> transform(List<DeviceMessageEntity> object) {
        List<DeviceMessage> mList = new ArrayList<>();
        for (DeviceMessageEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public DeviceMessageEntity transformToEntity(DeviceMessage object) {
        DeviceMessageEntity deviceMessage = new DeviceMessageEntity();
        deviceMessage.setDeviceMessageId(object.getDeviceMessageId());
        deviceMessage.setDateTime(object.getDateTime());
        deviceMessage.setMessage(object.getMessage());
        deviceMessage.setSendCallcenter(object.getSendCallcenter());
        if(object.getGuard() != null){
            deviceMessage.setGuard(guardTransformer.transformToEntity(object.getGuard()));
        }
        if(object.getDevice() != null){
            deviceMessage.setDevice(deviceTransformer.transformToEntity(object.getDevice()));
        }
        return deviceMessage;
    }

    @Override
    public List<DeviceMessageEntity> transformToEntity(List<DeviceMessage> object) {
        List<DeviceMessageEntity> mList = new ArrayList<>();
        for (DeviceMessage entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
