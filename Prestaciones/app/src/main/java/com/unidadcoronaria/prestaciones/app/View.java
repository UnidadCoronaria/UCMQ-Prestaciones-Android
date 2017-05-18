package com.unidadcoronaria.prestaciones.app;

import android.app.Activity;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface View {

    void displayError(String message);

    void showLoading();

    void hideLoading();

    Activity getActivity();
}
