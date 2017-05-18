package com.unidadcoronaria.prestaciones.data.entity.directions;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 12/17/2016.
 */

public class LegEntity {

    private DistanceEntity distance;
    private DurationEntity duration;
    private List<StepEntity> steps;

    public DistanceEntity getDistance() {
        return distance;
    }

    public void setDistance(DistanceEntity distance) {
        this.distance = distance;
    }

    public DurationEntity getDuration() {
        return duration;
    }

    public void setDuration(DurationEntity duration) {
        this.duration = duration;
    }

    public List<StepEntity> getSteps() {
        return steps;
    }

    public void setSteps(List<StepEntity> steps) {
        this.steps = steps;
    }
}
