package org.mindstormscop.drehkreisel;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
  public static void main(String[] args)  {
    EV3LargeRegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);
    EV3LargeRegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.C);
    DriveBehavior b = new DriveBehavior(leftWheel, rightWheel);
    RightTurnBehavior rt = new RightTurnBehavior(leftWheel, rightWheel, 0.3f);
    WaitBehavior s = new WaitBehavior(6000);
    Behavior[] bs = {b, rt, s};

    Arbitrator arbi = new Arbitrator(bs);
    arbi.start();
  }
}
