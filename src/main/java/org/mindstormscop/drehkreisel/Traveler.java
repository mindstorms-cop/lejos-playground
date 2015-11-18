package org.mindstormscop.drehkreisel;

import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;

public class Traveler {

  private final DifferentialPilot pilot;
  private final DistanceMeasureSensor dmSensor;
  private final PoseProvider pSensor;

  public Traveler(DifferentialPilot move, DistanceMeasureSensor dmSensor, PoseProvider pSensor) {
    this.pilot = move;
    this.dmSensor = dmSensor;
    this.pSensor = pSensor;
  }

  public void go() {
    pilot.forward();
    while (pilot.isMoving()) {
      if (dmSensor.check()) {
        pilot.stop();
      }
    }
    float dist = pilot.getMovement().getDistanceTraveled();
    System.out.println("Distance = " + dist);

    pilot.rotateLeft();

    while (pilot.isMoving()) {
      if (pSensor.getPose().getHeading() != 180) {
        pilot.quickStop();
      }
    }

    pilot.travel(dist);
  }
}
