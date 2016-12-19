package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Message;
import com.unidadcoronaria.prestaciones.data.entity.MessageEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MessageTransformer implements Transformer<MessageEntity, Message>, EntityTransformer<MessageEntity, Message> {

    @Override
    public MessageEntity transformToEntity(Message object) {
        MessageEntity entity = new MessageEntity();
        entity.setText(object.getText());
        entity.setDate(object.getDate());
        return entity;
    }

    @Override
    public List<MessageEntity> transformToEntity(List<Message> object) {
        List<MessageEntity> mList = new ArrayList<>();
        for (Message entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }

    @Override
    public Message transform(MessageEntity object) {
        Message entity = new Message();
        entity.setText(object.getText());
        entity.setDate(object.getDate());
        return entity;
    }

    @Override
    public List<Message> transform(List<MessageEntity> object) {
        List<Message> mList = new ArrayList<>();
        for (MessageEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
