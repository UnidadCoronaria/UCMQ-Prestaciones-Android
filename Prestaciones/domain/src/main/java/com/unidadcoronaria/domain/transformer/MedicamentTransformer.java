package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.prestaciones.data.entity.MedicamentEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.EntityTransformer;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicamentTransformer implements Transformer<MedicamentEntity, Medicament>, EntityTransformer<MedicamentEntity, Medicament> {

    @Override
    public Medicament transform(MedicamentEntity object) {
        Medicament entity = new Medicament();
        entity.setMedicamentId(object.getMedicamentId());
        entity.setName(object.getName());
        entity.setNumber(object.getNumber());
        entity.setActive(object.getActive());
        entity.setType(object.getType());
        entity.setAmmount(object.getAmmount());
        return entity;
    }

    @Override
    public List<Medicament> transform(List<MedicamentEntity> object) {
        List<Medicament> mList = new ArrayList<>();
        for (MedicamentEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }

    @Override
    public MedicamentEntity transformToEntity(Medicament object) {
        MedicamentEntity entity = new MedicamentEntity();
        entity.setMedicamentId(object.getMedicamentId());
        entity.setName(object.getName());
        entity.setNumber(object.getNumber());
        entity.setActive(object.getActive());
        entity.setType(object.getType());
        entity.setAmmount(object.getAmmount());
        return entity;
    }

    @Override
    public List<MedicamentEntity> transformToEntity(List<Medicament> object) {
        List<MedicamentEntity> mList = new ArrayList<>();
        for (Medicament entity: object) {
            mList.add(transformToEntity(entity));
        }
        return mList;
    }

}
