package org.mindstormscop.drehkreisel;


import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class DistanceMeasureSensor{

    private SampleProvider sp;
    private float distance;

    public DistanceMeasureSensor(EV3UltrasonicSensor uvSensor, float distance) {

        sp = uvSensor.getDistanceMode();
        this.distance = distance;
    }

    public boolean check() {
        float d = getDistance();
        System.out.printf("Distance: %f <? %f\n", d, distance);

        return d < distance;
    }

    public float getDistance() {
        float[] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);

        return sample[0];
    }
}
