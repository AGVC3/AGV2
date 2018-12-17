public class Notifications {

    private LED led;
    private Speaker speaker;

    public Notifications(int pinSpeaker) {
        this.speaker = new Speaker(pinSpeaker);
    }

    public void ledOn() {
        this.led.setAllLedPinsColorRed();
    }

    public void ledOff() {
        this.led.pinsAllOff();
    }

    public void truckHorn() {

    }

    public void noiseDrivingBackwards() {

    }
}
