import TI.BoeBot;

public class LineSensor {

    private int pin;

    public LineSensor(int pin) {
        this.pin = pin;
    }

    public boolean isState() {
        //System.out.println(BoeBot.analogRead(pin));
        if (BoeBot.analogRead(this.pin) > 1500) {
            return true;
        } else {
            return false;
        }
    }
}
