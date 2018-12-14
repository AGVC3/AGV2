import TI.BoeBot;


public class LineSensor{

    private int pin;

    public LineSensor (int pin) {
        this.pin = pin;
    }

    public boolean isState() {
        if (BoeBot.analogRead(pin) > 1000) {
            return true;
        } else {
            return false;
        }
    }
}
