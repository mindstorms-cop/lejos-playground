package org.mindstormscop.drehkreisel;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
  public static void main(String[] args)  {
    DrehBehavior b = new DrehBehavior();
    WaitBehavior s = new WaitBehavior(4000);
    Behavior[] bs = {b, s};

    Arbitrator arbi = new Arbitrator(bs);
    arbi.start();
  }
}
