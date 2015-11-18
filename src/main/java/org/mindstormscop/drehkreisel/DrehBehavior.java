package org.mindstormscop.drehkreisel;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.subsumption.Behavior;

public class DrehBehavior implements Behavior {
    private boolean suppressed = false;
    private EV3LargeRegulatedMotor leftWheel;
    private EV3LargeRegulatedMotor rightWheel;

    public DrehBehavior() {
        leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);
        rightWheel = new EV3LargeRegulatedMotor(MotorPort.C);
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

        System.out.printf("Speeding up to %f", leftWheel.getMaxSpeed());

        leftWheel.forward();
        rightWheel.backward();
        while( !suppressed )
            Thread.yield();
        leftWheel.flt(true); // clean up
        rightWheel.flt(true);
    }

    @Override
    public void suppress() {
        suppressed = true;
    }
}
