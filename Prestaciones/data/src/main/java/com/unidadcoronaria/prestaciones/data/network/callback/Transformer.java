package com.unidadcoronaria.prestaciones.data.network.callback;

/**
 * @author Fernando.Perez
 * @since 0.1
 */
public interface Transformer<E, D> {

    D transform(E object);
}
