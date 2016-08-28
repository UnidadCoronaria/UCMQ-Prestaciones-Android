package com.unidadcoronaria.domain;


import org.greenrobot.eventbus.EventBus;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class BusProvider {
    //region Properties
    private static BusProvider instance = new BusProvider();
    private final EventBus defaultBus;
    //endregion

    //region Constructor
    private BusProvider() {
        defaultBus = EventBus.getDefault();
    }
    //endregion

    //region Public Static Implementation
    public static EventBus getDefaultBus() {
        return instance.defaultBus;
    }
    //endregion
}
