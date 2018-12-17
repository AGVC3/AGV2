package com.alg;

import TI.BoeBot;

import java.util.ArrayList;

public class LineSensor implements Updatable {

    private LineSensorCallback lineDetect;

    public LineSensor(LineSensorCallback lineDetect) {
        this.lineDetect = lineDetect;
    }

    public void update() {
        ArrayList<Boolean> linesDetected = new ArrayList<>();

        if (BoeBot.analogRead(0) > 1000) {
            linesDetected.add(true);
        } else {
            linesDetected.add(false);
        }
        if (BoeBot.analogRead(1) > 1000) {
            linesDetected.add(true);
        } else {
            linesDetected.add(false);
        }
        if (BoeBot.analogRead(2) > 1000) {
            linesDetected.add(true);
        } else {
            linesDetected.add(false);
        }

        this.lineDetect.lineSensorDetect(linesDetected);
    }
}
