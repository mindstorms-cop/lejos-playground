package org.mindstormscop.drehkreisel;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.internal.io.SystemSettings;
import lejos.robotics.GyroscopeAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.CompassPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.GyroDirectionFinder;

public class Main {
  public static void main(String[] args)  {
    Wheel wheel1 = WheeledChassis.modelWheel(Motor.A, 56.0).offset(-60);
    Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.0).offset(60);
    Chassis chassis = new WheeledChassis(new Wheel[]{wheel1, wheel2}, 2);
    MovePilot pilot = new MovePilot(chassis);

    double angularSpeed = chassis.getMaxAngularSpeed() * 0.2;
    System.out.printf("Angular speed: %f\n", angularSpeed);
    double linearSpeed = chassis.getMaxLinearSpeed() * 0.4;
    System.out.printf("Linear speed: %f\n", linearSpeed);

    pilot.setAngularSpeed(angularSpeed);
    pilot.setLinearSpeed(linearSpeed);

    EV3GyroSensor gs = new EV3GyroSensor(SensorPort.S4);
    PoseProvider pp = new CompassPoseProvider(
            pilot,
            new GyroDirectionFinder(new GyroscopeAdapter(gs.getAngleAndRateMode(), 60))
    );
    Traveler traveler = new Traveler(
        pilot,
        new DistanceMeasureSensor(new EV3UltrasonicSensor(SensorPort.S1), 0.15f),
        pp
    );

    traveler.go();
  }
}
