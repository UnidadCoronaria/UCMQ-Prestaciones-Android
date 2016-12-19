package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Mobile;
import com.unidadcoronaria.prestaciones.data.entity.MobileEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MobileTransformer implements Transformer<MobileEntity, Mobile>,EntityTransformer<MobileEntity, Mobile> {

    private MobileTypeTransformer mobileTypeTransformer = new MobileTypeTransformer();
    private CompanyTransformer companyTransformer = new CompanyTransformer();

    @Override
    public Mobile transform(MobileEntity object) {
        Mobile mobile = new Mobile();
        mobile.setMobileId(object.getMobileId());
        mobile.setActive(object.getActive());
        mobile.setAlias(object.getAlias());
        mobile.setCompany(companyTransformer.transform(object.getCompany()));
        mobile.setName(object.getName());
        mobile.setMobileType(mobileTypeTransformer.transform(object.getMobileType()));
        return mobile ;
    }

    @Override
    public List<Mobile> transform(List<MobileEntity> object) {
        List<Mobile> mList = new ArrayList<>();
        for (MobileEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public MobileEntity transformToEntity(Mobile object) {
        MobileEntity mobile = new MobileEntity();
        mobile.setMobileId(object.getMobileId());
        mobile.setActive(object.getActive());
        mobile.setAlias(object.getAlias());
        mobile.setCompany(companyTransformer.transformToEntity(object.getCompany()));
        mobile.setName(object.getName());
        mobile.setMobileType(mobileTypeTransformer.transformToEntity(object.getMobileType()));
        return mobile ;
    }

    @Override
    public List<MobileEntity> transformToEntity(List<Mobile> object) {
        List<MobileEntity> mList = new ArrayList<>();
        for (Mobile entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
