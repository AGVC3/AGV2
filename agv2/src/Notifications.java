public class Notifications{

    private LEDControl ledControl;

    public Notifications() {
        this.ledControl = new LEDControl();
    }

    public void ledOn(String color) {
        this.ledControl.setAllLedPinsColor(color);
    }

    public void ledOff() {
        this.ledControl.pinsAllOff();
    }

    public LEDControl getLedControl() {
        return this.ledControl;
    }
}
