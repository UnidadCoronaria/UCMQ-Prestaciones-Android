package com.unidadcoronaria.prestaciones;

import android.app.Application;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class App extends Application {

    private static App INSTANCE;
    //region Public Static Implementation
    public static App getInstance() {
        return INSTANCE;
    }
    //endregion

    //region Application
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

}