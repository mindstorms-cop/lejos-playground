package org.mindstormscop.drehkreisel;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.GyroscopeAdapter;
import lejos.robotics.localization.CompassPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.GyroDirectionFinder;

public class Main {
  public static void main(String[] args)  {
    EV3LargeRegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);
    EV3LargeRegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.C);
//    DriveBehavior b = new DriveBehavior(leftWheel, rightWheel);
//    RightTurnBehavior rt = new RightTurnBehavior(leftWheel, rightWheel, 0.3f);
//    WaitBehavior s = new WaitBehavior(6000);
//    Behavior[] bs = {b, rt, s};
//
//    Arbitrator arbi = new Arbitrator(bs);
//    arbi.start();


    DifferentialPilot pilot = new DifferentialPilot(56.0f, 120.0f, leftWheel, rightWheel);
    EV3GyroSensor gs = new EV3GyroSensor(SensorPort.S4);
    PoseProvider pp = new CompassPoseProvider(
            pilot,
            new GyroDirectionFinder(new GyroscopeAdapter(gs.getAngleAndRateMode(), 60))
    );
    Traveler traveler = new Traveler(
        pilot,
        new DistanceMeasureSensor(new EV3UltrasonicSensor(SensorPort.S1), 0.08f),
        pp
    );
    traveler.go();

  }
}
