package org.mindstormscop.drehkreisel;

import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;

public class Traveler {

  private final MovePilot pilot;
  private final DistanceMeasureSensor dmSensor;
  private final PoseProvider pSensor;

  public Traveler(MovePilot move, DistanceMeasureSensor dmSensor, PoseProvider pSensor) {
    this.pilot = move;
    this.dmSensor = dmSensor;
    this.pSensor = pSensor;
  }

  public void go() {
    pilot.forward();

    float dist = 0.0f;
    while (pilot.isMoving()) {
      if (dmSensor.check()) {
        System.out.println("Stopping");
        dist = pilot.getMovement().getDistanceTraveled();
        pilot.stop();
      }
    }
    System.out.println("Distance = " + dist);

//    pilot.stop();

//    pilot.travel(-dist);
//
//    pilot.travel(dist);


    System.out.println("Rotating");
    float initialAngle = pSensor.getPose().getHeading();

    pilot.rotate(360, true);

    while (pilot.isMoving()) {
      float currentAngle = pSensor.getPose().getHeading();
      if (Math.abs(currentAngle - initialAngle) > 180) {
        pilot.stop();
      }
    }

//    while (pSensor.getPose().getHeading() != 180) {
//      System.out.println("Moving");
//      System.out.println(pSensor.getPose().getHeading());
////      if (pSensor.getPose().getHeading() == 180) {
////        System.out.println("Stop rotating");
////        pilot.stop();
////      }
//    }
//    pilot.stop();

    System.out.println("Driving back");

    pilot.travel(dist);
  }
}
