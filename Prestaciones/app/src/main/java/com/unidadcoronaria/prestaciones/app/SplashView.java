package com.unidadcoronaria.prestaciones.app;

/**
 * @author Agustin.Bala
 * @since 3.0.0
 */
public interface SplashView extends View {

    void onProgressUpdate(Integer progress);

    void showGooglePlayServicesError();

    void showGooglePlayServicesDialog(int codeError);

    void callNextActivity();
}
