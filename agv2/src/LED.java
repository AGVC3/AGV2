package com.alg;

import TI.BoeBot;

public class LED {

    private int pin;

    public LED(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return this.pin;
    }

    public void ledOn() {
        BoeBot.digitalWrite(this.pin, true);
    }

    public void ledOff() {
        BoeBot.digitalWrite(this.pin, false);
    }
}
