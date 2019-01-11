public class Notifications {

    private LED led;
    private Speaker speaker;

    public Notifications(int pinLed, int pinSpeaker) {
        this.led = new LED(pinLed);
        this.speaker = new Speaker(pinSpeaker);
    }

    public void ledOn() {
        this.led.ledOn();
    }

    public void ledOff() {
        this.led.ledOff();
    }

    public void truckHorn() {

    }

    public void noiseDrivingBackwards() {

    }
}
