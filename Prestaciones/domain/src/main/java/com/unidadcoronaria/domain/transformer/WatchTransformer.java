package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Watch;
import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class WatchTransformer implements Transformer<WatchEntity, Watch> {

    private ResourceTransformer resourceTransformer = new ResourceTransformer();

    @Override
    public Watch transform(WatchEntity object) {
        Watch watch = new Watch();
        watch.setCaseComplete(object.getCaseComplete());
        watch.setClean(object.getClean());
        watch.setOxygenFull(object.getOxygenFull());
        if (object.getResource() != null) {
            watch.setResource(resourceTransformer.transform(object.getResource()));
        }
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
}
