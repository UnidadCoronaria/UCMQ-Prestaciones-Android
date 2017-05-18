package com.unidadcoronaria.prestaciones.data.entity.directions;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 12/17/2016.
 */

public class RouteResponseEntity {

    private List<RouteEntity> routes;
    private String status;

    public List<RouteEntity> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteEntity> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
