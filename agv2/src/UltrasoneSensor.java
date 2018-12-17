package com.alg;

import TI.BoeBot;

public class UltrasoneSensor implements Updatable {

    private UltrasoneSensorCallback onDetect;

    public UltrasoneSensor(UltrasoneSensorCallback onDetect) {
        this.onDetect = onDetect;
    }

    public void update() {
        BoeBot.digitalWrite(10, true);
        BoeBot.wait(0,500);
        BoeBot.digitalWrite(10, false);

        int pulse = BoeBot.pulseIn(11, true, 10000);
        this.onDetect.ultrasoneDetect(pulse);
    }
}
