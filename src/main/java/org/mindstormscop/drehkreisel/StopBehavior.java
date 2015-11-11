package org.mindstormscop.drehkreisel;

import lejos.robotics.subsumption.Behavior;

import java.util.Date;

public class StopBehavior implements Behavior {
    DistanceMeasureSensor sensor;

    public StopBehavior(float distance) {
        sensor = new DistanceMeasureSensor(distance);
    }

    @Override
    public boolean takeControl() {
        return sensor.check();
    }

    @Override
    public void action() {
        System.out.println("Going to bed.");
        System.exit(0);
    }

    @Override
    public void suppress() {

    }
}
