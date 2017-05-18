package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface MainDrawerView extends View {

    void showFragment(BaseFragment fragment);
}
