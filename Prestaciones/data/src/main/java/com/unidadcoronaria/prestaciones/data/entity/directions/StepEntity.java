package com.unidadcoronaria.prestaciones.data.entity.directions;

/**
 * Created by AGUSTIN.BALA on 12/17/2016.
 */

public class StepEntity {

    private PolylineEntity polyline;
    private PointEntity start_location;
    private PointEntity end_location;

    public PolylineEntity getPolyline() {
        return polyline;
    }

    public void setPolyline(PolylineEntity polyline) {
        this.polyline = polyline;
    }

    public PointEntity getStart_location() {
        return start_location;
    }

    public void setStart_location(PointEntity start_location) {
        this.start_location = start_location;
    }

    public PointEntity getEnd_location() {
        return end_location;
    }

    public void setEnd_location(PointEntity end_location) {
        this.end_location = end_location;
    }
}
