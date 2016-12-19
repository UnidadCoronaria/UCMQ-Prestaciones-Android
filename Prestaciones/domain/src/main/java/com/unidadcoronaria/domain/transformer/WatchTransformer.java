package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Watch;
import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class WatchTransformer implements Transformer<WatchEntity, Watch>, EntityTransformer<WatchEntity, Watch> {

    private ResourceTransformer resourceTransformer = new ResourceTransformer();
    private WatchItemTransformer watchItemTransformer = new WatchItemTransformer();

    @Override
    public Watch transform(WatchEntity object) {
        Watch watch = new Watch();
        if(object.getItemList() != null){
            watch.setItemList(watchItemTransformer.transform(object.getItemList()));
        }
        if (object.getResource() != null) {
            watch.setResource(resourceTransformer.transform(object.getResource()));
        }
        watch.setNote(object.getNote());
        return watch;
    }

    @Override
    public List<Watch> transform(List<WatchEntity> object) {
        List<Watch> mList = new ArrayList<>();
        for (WatchEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public WatchEntity transformToEntity(Watch object) {
        WatchEntity watch = new WatchEntity();
        if(object.getItemList() != null){
            watch.setItemList(watchItemTransformer.transformToEntity(object.getItemList()));
        }
        if (object.getResource() != null) {
            watch.setResource(resourceTransformer.transformToEntity(object.getResource()));
        }
        watch.setNote(object.getNote());
        return watch;
    }

    @Override
    public List<WatchEntity> transformToEntity(List<Watch> object) {
        List<WatchEntity> mList = new ArrayList<>();
        for (Watch entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
