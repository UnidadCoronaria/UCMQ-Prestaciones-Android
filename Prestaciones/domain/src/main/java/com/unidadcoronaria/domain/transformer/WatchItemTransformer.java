package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.WatchItem;
import com.unidadcoronaria.prestaciones.data.entity.WatchItemEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class WatchItemTransformer implements Transformer<WatchItemEntity, WatchItem>, EntityTransformer<WatchItemEntity,WatchItem> {


    @Override
    public WatchItem transform(WatchItemEntity object) {
        WatchItem watchItem = new WatchItem();
        watchItem.setName(object.getName());
        watchItem.setNote(object.getNote());
        watchItem.setStatus(object.getStatus());
        return watchItem;
    }

    @Override
    public List<WatchItem> transform(List<WatchItemEntity> object) {
        List<WatchItem> mList = new ArrayList<>();
        for (WatchItemEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public WatchItemEntity transformToEntity(WatchItem object) {
        WatchItemEntity watchItem = new WatchItemEntity();
        watchItem.setName(object.getName());
        watchItem.setNote(object.getNote());
        watchItem.setStatus(object.getStatus());
        return watchItem;
    }

    @Override
    public List<WatchItemEntity> transformToEntity(List<WatchItem> object) {
        List<WatchItemEntity> mList = new ArrayList<>();
        for (WatchItem entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
