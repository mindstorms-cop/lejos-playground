package org.mindstormscop.drehkreisel;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;

public class Traveler {

  private final DifferentialPilot pilot;
  private final DistanceMeasureSensor dmSensor;

  public Traveler(DifferentialPilot move, DistanceMeasureSensor dmSensor) {
    this.pilot = move;
    this.dmSensor = dmSensor;
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
    pilot.rotate(180);
    pilot.travel(dist);

  }
}
