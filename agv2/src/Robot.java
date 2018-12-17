package com.alg;

import java.util.ArrayList;

public class Robot implements LineSensorCallback, UltrasoneSensorCallback, RemoteControlCallback {

    private ArrayList<Updatable> updatables;
    private Driver driver;
    private Notifications notifications;

    public Robot() {
        this.driver = new Driver();
        this.notifications = new Notifications(5, 6);

        this.updatables = new ArrayList<>();

        this.updatables.add(new LineSensor(this));
        this.updatables.add(new UltrasoneSensor(this));
        this.updatables.add(new RemoteControl());
        this.updatables.add(this.driver.getLeft());
        this.updatables.add(this.driver.getRight());
    }

    public void updateAll() {
        for (Updatable u : this.updatables) {
            u.update();
        }
    }

    public void lineSensorDetect(ArrayList<Boolean> linesDetected) {
        System.out.println(linesDetected);
        if (!linesDetected.get(0) && linesDetected.get(1) && !linesDetected.get(2)) { //straight forward
            this.driver.goToSpeed(1600);
        } else if (linesDetected.get(0) && !linesDetected.get(1) && !linesDetected.get(2)) { //turn to the left
            this.driver.emergencyBreak();
            this.driver.turn("Left");
        } else if (!linesDetected.get(0) && !linesDetected.get(1) && linesDetected.get(2)) { //turn to the right
            this.driver.emergencyBreak();
            this.driver.turn("Right");
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2)) { //crossroads logic
            ////////////////////////////////
        }
    }

    public void ultrasoneDetect(int pulse) {
        System.out.println(pulse);
        if (pulse > 17 && pulse < 500) {
            this.driver.goToSpeed(1400);
            this.notifications.noiseDrivingBackwards();
        } else if (pulse > 500 && pulse < 1000) {
            this.driver.emergencyBreak();
            this.notifications.ledOn();
            this.notifications.truckHorn();
        } else {
            this.driver.goToSpeed(1600);
        }
    }
}
