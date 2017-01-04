package com.unidadcoronaria.prestaciones.util;

import com.unidadcoronaria.domain.model.Provider;
import com.unidadcoronaria.prestaciones.App;

/**
 * Created by AGUSTIN.BALA on 12/22/2016.
 */

public class SessionHelper {

    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_GUARD_ID = "KEY_GUARD_ID";

    public static void saveProvider(Provider provider){
        SharedPreferencesHelper.putString(App.getInstance(), KEY_USER_NAME, provider.getName());
        SharedPreferencesHelper.putString(App.getInstance(), KEY_USER_ID, provider.getProviderId().toString());
        SharedPreferencesHelper.putString(App.getInstance(), KEY_GUARD_ID, provider.getGuardId().toString());
    }

    public static String getUser() {
        return SharedPreferencesHelper.getString(App.getInstance(), KEY_USER_ID);
    }

    public static String getGuardId() {
        return SharedPreferencesHelper.getString(App.getInstance(), KEY_GUARD_ID);
    }
}
