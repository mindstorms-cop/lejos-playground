package org.mindstormscop.drehkreisel;


import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class DistanceMeasureSensor{

    private SampleProvider sp;
    private float distance;

    public DistanceMeasureSensor(float distance) {
        EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S3);
        sp = sensor.getDistanceMode();
        this.distance = distance;
    }

    public boolean check() {
        float[] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        return sample[0] < distance;
    }
}
