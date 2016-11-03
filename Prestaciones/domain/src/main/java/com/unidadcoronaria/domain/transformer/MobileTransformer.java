package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Mobile;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.data.entity.MobileEntity;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MobileTransformer implements Transformer<MobileEntity, Mobile> {

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
}
