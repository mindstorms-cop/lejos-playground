package org.mindstormscop.drehkreisel;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.subsumption.Behavior;

public class DriveBehavior implements Behavior {
    private boolean suppressed = false;
    private EV3LargeRegulatedMotor leftWheel;
    private EV3LargeRegulatedMotor rightWheel;

    public DriveBehavior(EV3LargeRegulatedMotor leftWheel, EV3LargeRegulatedMotor rightWheel) {
        this.leftWheel = leftWheel;
        this.rightWheel = rightWheel;
    }

    @Override
    public boolean takeControl() {
        return true;
    }

    @Override
    public void action() {
        suppressed = false;

        leftWheel.setAcceleration(500);
        rightWheel.setAcceleration(500);
        leftWheel.setSpeed(250);
        rightWheel.setSpeed(250);

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
