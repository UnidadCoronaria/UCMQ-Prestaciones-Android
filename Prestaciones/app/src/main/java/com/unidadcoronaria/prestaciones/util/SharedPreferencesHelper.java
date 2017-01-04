package com.unidadcoronaria.prestaciones.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Agustin.Bala
 */
public class SharedPreferencesHelper {
    private static final int DEFAULT_INT = Integer.MIN_VALUE;
    private static final String DEFAULT_STRING = "";
    private static final Boolean DEFAULT_BOOLEAN = false;

    private static SharedPreferences getDefaultSharedPreferences(Context aContext) {
        return PreferenceManager.getDefaultSharedPreferences(aContext);
    }

    public static boolean hasKey(Context aContext, String aKey) {
        return getDefaultSharedPreferences(aContext).contains(aKey);
    }

    public static void removeKey(Context aContext, String aKey) {
        getDefaultSharedPreferences(aContext).edit().remove(aKey).commit();
    }

    public static Integer getInteger(Context aContext, String aKey) {
        return getInteger(aContext, aKey, DEFAULT_INT);
    }

    public static Integer getInteger(Context aContext, String aKey, int aDefaultValue) {
        return getDefaultSharedPreferences(aContext).getInt(aKey, aDefaultValue);
    }

    public static void putInteger(Context aContext, String aKey, Integer aValue) {
        getDefaultSharedPreferences(aContext).edit().putInt(aKey, aValue).commit();
    }

    public static String getString(Context aContext, String aKey) {
        return getDefaultSharedPreferences(aContext).getString(aKey, DEFAULT_STRING);
    }

    public static void putString(Context aContext, String aKey, String aValue) {
        getDefaultSharedPreferences(aContext).edit().putString(aKey, aValue).commit();
    }

    public static Boolean getBoolean(Context aContext, String aKey) {
        return getDefaultSharedPreferences(aContext).getBoolean(aKey, DEFAULT_BOOLEAN);
    }

    public static void putBoolean(Context aContext, String aKey, boolean aValue) {
        getDefaultSharedPreferences(aContext).edit().putBoolean(aKey, aValue).commit();
    }
}