package org.mindstormscop.drehkreisel;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

public class RightTurnBehavior implements Behavior {
    private boolean suppressed = false;
    private DistanceMeasureSensor sensor;
    private EV3LargeRegulatedMotor leftWheel;
    private EV3LargeRegulatedMotor rightWheel;

    public RightTurnBehavior(EV3LargeRegulatedMotor leftWheel, EV3LargeRegulatedMotor rightWheel, EV3UltrasonicSensor uvSensor, float distance) {
        sensor = new DistanceMeasureSensor(uvSensor, distance);
        this.leftWheel = leftWheel;
        this.rightWheel = rightWheel;
    }

    @Override
    public boolean takeControl() {
        return sensor.check();
    }

    @Override
    public void action() {
        suppressed = false;

        leftWheel.setAcceleration(500);
        rightWheel.setAcceleration(500);

        leftWheel.setSpeed(250);
        rightWheel.setSpeed(250/4);

        leftWheel.forward();
        rightWheel.forward();
        while( !suppressed )
            Thread.yield();
        leftWheel.stop(true);
        rightWheel.stop(true);
    }

    @Override
    public void suppress() {
        suppressed = true;
    }
}
