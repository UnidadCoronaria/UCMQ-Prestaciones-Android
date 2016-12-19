package com.unidadcoronaria.prestaciones.app.viewpager;

import com.unidadcoronaria.domain.model.Watch;
import com.unidadcoronaria.prestaciones.app.View;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface WatchView extends View{

    void onWatchRetrieved(Watch watch);

    void onWatchItemsCompleted();

    void onWatchItemsIncompleted();

}
