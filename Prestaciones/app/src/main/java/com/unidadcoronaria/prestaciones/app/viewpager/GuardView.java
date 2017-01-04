package com.unidadcoronaria.prestaciones.app.viewpager;

import com.unidadcoronaria.domain.model.TypeMobileObservation;
import com.unidadcoronaria.prestaciones.app.View;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface GuardView extends View {

    void onMobileObservationRetrieved(List<TypeMobileObservation> mobileObservation);

    void onMobileObservationItemsCompleted();

    void onWatchMobileObservationItemsIncompleted();

}
