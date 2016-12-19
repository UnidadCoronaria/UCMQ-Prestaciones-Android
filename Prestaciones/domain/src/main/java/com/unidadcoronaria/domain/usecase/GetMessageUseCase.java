
package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Message;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.domain.transformer.MessageTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MessageEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetMessageUseCase extends UseCase<Resource> {

    private final MessageTransformer transformer = new MessageTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMessage(new SuccessFailureCallBack<List<MessageEntity>>() {
            @Override
            public void onSuccess(List<MessageEntity> object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                GetMessageUseCase.super.onFailure(message);
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<Message> messageList;

        public SuccessResponse(List<Message> messageEntityList) {
            this.messageList = messageEntityList;
        }

        public List<Message> getMessageList() {
            return messageList;
        }
    }
    //endregion
}
