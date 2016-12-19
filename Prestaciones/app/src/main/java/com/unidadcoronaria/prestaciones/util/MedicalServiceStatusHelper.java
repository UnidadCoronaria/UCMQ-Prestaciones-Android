package com.unidadcoronaria.prestaciones.util;

import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.R;

/**
 * Created by AGUSTIN.BALA on 12/18/2016.
 */

public class MedicalServiceStatusHelper {

    public static String getStatusName(Integer status){
        switch (status){
            case 1:
                return App.getInstance().getString(R.string.medical_service_status_assigned);

            case 2:
                return App.getInstance().getString(R.string.medical_service_status_going);
                
            case 3:
                return App.getInstance().getString(R.string.medical_service_status_on_site);
                
            case 4:
                return App.getInstance().getString(R.string.medical_service_status_support);
                
            case 5:
                return App.getInstance().getString(R.string.medical_service_status_available);
                
            case 6:
                return App.getInstance().getString(R.string.medical_service_status_done);
                
            case 7:
                return App.getInstance().getString(R.string.medical_service_status_released);
                
            case 8:
                return App.getInstance().getString(R.string.medical_service_status_cancelled);
                
            case 9:
                return App.getInstance().getString(R.string.medical_service_status_to_destiny);
                
            case 10:
                return App.getInstance().getString(R.string.medical_service_status_in_destiny);
                
            case 11:
                return App.getInstance().getString(R.string.medical_service_status_to_origin);
                
            case 12:
                return App.getInstance().getString(R.string.medical_service_status_in_origin);
                

        }
        return "";
    }
}
