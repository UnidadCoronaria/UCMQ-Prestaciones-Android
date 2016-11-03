package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.MobileType;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.data.entity.MobileTypeEntity;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MobileTypeTransformer implements Transformer<MobileTypeEntity, MobileType> {

    @Override
    public MobileType transform(MobileTypeEntity object) {
        MobileType mobileType = new MobileType();
        mobileType.setMobileTypeId(object.getMobileTypeId());
        mobileType.setName(object.getName());
        mobileType.setAlias(object.getAlias());
        return mobileType;
    }

    @Override
    public List<MobileType> transform(List<MobileTypeEntity> object) {
        List<MobileType> mList = new ArrayList<>();
        for (MobileTypeEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
