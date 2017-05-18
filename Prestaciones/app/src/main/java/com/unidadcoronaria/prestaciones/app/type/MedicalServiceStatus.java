package com.unidadcoronaria.prestaciones.app.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by AGUSTIN.BALA on 11/15/2016.
 */

@IntDef({MedicalServiceStatus.ASSIGNED, MedicalServiceStatus.GOING, MedicalServiceStatus.ON_SITE, MedicalServiceStatus.SUPPORT,
        MedicalServiceStatus.AVAILABLE, MedicalServiceStatus.DONE, MedicalServiceStatus.RELEASED, MedicalServiceStatus.CANCELLED, MedicalServiceStatus.TO_DESTINY,
        MedicalServiceStatus.IN_DESTINY, MedicalServiceStatus.TO_ORIGIN, MedicalServiceStatus.IN_ORIGIN})
@Retention(RetentionPolicy.SOURCE)
public @interface MedicalServiceStatus {

    int ASSIGNED = 1;
    int GOING = 2;
    int ON_SITE = 3;
    int SUPPORT = 4;
    int AVAILABLE = 5;
    int DONE = 6;
    int RELEASED = 7;
    int CANCELLED = 8;
    int TO_DESTINY = 9;
    int IN_DESTINY = 10;
    int TO_ORIGIN = 11;
    int IN_ORIGIN = 12;
}
