package org.mindstormscop.drehkreisel;

import lejos.robotics.subsumption.Behavior;

import java.util.Date;

public class WaitBehavior implements Behavior {
    long waitTime;
    Date startTime;

    public WaitBehavior(long time) {
        waitTime = time;
        startTime = new Date();
    }

    @Override
    public boolean takeControl() {
        return (new Date().getTime() - startTime.getTime()) > waitTime;
    }

    @Override
    public void action() {
        System.out.println("Going to bed.");
        System.exit(0);
    }

    @Override
    public void suppress() {

    }
}
