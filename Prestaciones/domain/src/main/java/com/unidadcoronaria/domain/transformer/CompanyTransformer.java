package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Company;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.data.entity.CompanyEntity;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class CompanyTransformer implements Transformer<CompanyEntity, Company> {

    @Override
    public Company transform(CompanyEntity object) {
        Company company = new Company();
        company.setCompanyId(object.getCompanyId());
        company.setName(object.getName());
        company.setFantasyName(object.getFantasyName());
        return company;
    }

    @Override
    public List<Company> transform(List<CompanyEntity> object) {
        List<Company> mList = new ArrayList<>();
        for (CompanyEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
