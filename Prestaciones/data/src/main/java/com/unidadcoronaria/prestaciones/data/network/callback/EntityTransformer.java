package com.unidadcoronaria.prestaciones.data.network.callback;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 11/9/2016.
 */

public interface EntityTransformer<E, D>  {

    E transformToEntity(D object);
    List<E> transformToEntity(List<D> object);

}
