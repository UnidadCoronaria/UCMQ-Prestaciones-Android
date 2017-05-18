package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Cabin;
import com.unidadcoronaria.prestaciones.data.entity.CabinEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class CabinTransformer implements Transformer<CabinEntity, Cabin>, EntityTransformer<CabinEntity, Cabin> {

    private CompanyTransformer companyTransformer = new CompanyTransformer();

    @Override
    public Cabin transform(CabinEntity object) {
        Cabin cabin = new Cabin();
        cabin.setCabinId(object.getCabinId());
        cabin.setName(object.getName());
        cabin.setLetter(object.getLetter());
        if(object.getCompany() != null) {
            cabin.setCompany(companyTransformer.transform(object.getCompany()));
        }
        return cabin;
    }

    @Override
    public List<Cabin> transform(List<CabinEntity> object) {
        List<Cabin> mList = new ArrayList<>();
        for (CabinEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public CabinEntity transformToEntity(Cabin object) {
        CabinEntity cabin = new CabinEntity();
        cabin.setCabinId(object.getCabinId());
        cabin.setName(object.getName());
        cabin.setLetter(object.getLetter());
        if(object.getCompany() != null) {
            cabin.setCompany(companyTransformer.transformToEntity(object.getCompany()));
        }
        return cabin;
    }

    @Override
    public List<CabinEntity> transformToEntity(List<Cabin> object) {
        List<CabinEntity> mList = new ArrayList<>();
        for (Cabin entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }
}
