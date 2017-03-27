package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.CallReason;
import com.unidadcoronaria.domain.model.Device;
import com.unidadcoronaria.prestaciones.data.entity.CallReasonEntity;
import com.unidadcoronaria.prestaciones.data.entity.DeviceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class CallReasonTransformer implements Transformer<CallReasonEntity, CallReason>, EntityTransformer<CallReasonEntity, CallReason> {

    @Override
    public CallReason transform(CallReasonEntity object) {
        CallReason callReason = new CallReason();
        callReason.setCallReasonMedicalServiceId(object.getCallReasonMedicalServiceId());
        callReason.setName(object.getName());
        return callReason;
    }

    @Override
    public List<CallReason> transform(List<CallReasonEntity> object) {
        List<CallReason> mList = new ArrayList<>();
        for (CallReasonEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public CallReasonEntity transformToEntity(CallReason object) {
        CallReasonEntity callReasonEntity = new CallReasonEntity();
        callReasonEntity.setCallReasonMedicalServiceId(object.getCallReasonMedicalServiceId());
        callReasonEntity.setName(object.getName());
        return callReasonEntity;
    }

    @Override
    public List<CallReasonEntity> transformToEntity(List<CallReason> object) {
        List<CallReasonEntity> mList = new ArrayList<>();
        for (CallReason entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
