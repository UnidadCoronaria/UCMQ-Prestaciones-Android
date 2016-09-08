package com.unidadcoronaria.prestaciones.data.network.callback;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface Transformer<E, D> {

    D transform(E object);
    List<D> transform(List<E> object);

}
