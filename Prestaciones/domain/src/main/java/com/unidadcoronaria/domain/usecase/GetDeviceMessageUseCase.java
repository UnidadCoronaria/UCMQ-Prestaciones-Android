
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
public class GetDeviceMessageUseCase extends UseCase<List<DeviceMessage>> {

    private final DeviceMessageTransformer transformer = new DeviceMessageTransformer();
    private Integer guardId;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMessage(new SuccessFailureCallBack<List<DeviceMessageEntity>>() {
            @Override
            public void onSuccess(List<DeviceMessageEntity> object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                GetDeviceMessageUseCase.super.onFailure(message);
            }
        }, this.guardId);
    }

    public void setData(Integer watchId){
        this.guardId = watchId;
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<DeviceMessage> messageList;

        public SuccessResponse(List<DeviceMessage> messageEntityList) {
            this.messageList = messageEntityList;
        }

        public List<DeviceMessage> getMessageList() {
            return messageList;
        }
    }
    //endregion
}
