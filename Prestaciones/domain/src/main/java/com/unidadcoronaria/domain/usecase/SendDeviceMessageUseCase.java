
package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.DeviceMessage;
import com.unidadcoronaria.domain.transformer.DeviceMessageTransformer;
import com.unidadcoronaria.prestaciones.data.entity.DeviceMessageEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SendDeviceMessageUseCase extends UseCase<DeviceMessage> {

    private DeviceMessage DeviceMessage;
    private final DeviceMessageTransformer transformer = new DeviceMessageTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().sendMessage(new SuccessFailureCallBack<DeviceMessageEntity>() {
            @Override
            public void onSuccess(DeviceMessageEntity object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String DeviceMessage) {
                SendDeviceMessageUseCase.super.onFailure(DeviceMessage);
            }
        }, transformer.transformToEntity(DeviceMessage));
    }

    public void setData(DeviceMessage DeviceMessage){
        this.DeviceMessage = DeviceMessage;
    }

    //region Inner Classes
    public static class SuccessResponse {

        private DeviceMessage DeviceMessage;

        public SuccessResponse(DeviceMessage DeviceMessage) {
            this.DeviceMessage = DeviceMessage;
        }

        public DeviceMessage getDeviceMessage() {
            return DeviceMessage;
        }
    }
    //endregion
}
