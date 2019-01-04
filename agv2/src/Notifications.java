public class Notifications{

    private LEDControl ledControl;
    private Speaker speaker;

    public Notifications(int pinSpeaker) {
        this.speaker = new Speaker(pinSpeaker);
        this.ledControl = new LEDControl();
    }

    public void ledOn(String color) {
        this.ledControl.setAllLedPinsColor(color);
    }

    public void ledOff() {
        this.ledControl.pinsAllOff();
    }

    public void truckHorn() {

    }

    public void noiseDrivingBackwards() {

    }

    public LEDControl getLedControl() {
        return ledControl;
    }
}
