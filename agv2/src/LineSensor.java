import TI.BoeBot;

public class LineSensor {

    private int pin;

    public LineSensor(int pin) {
        this.pin = pin;
    }

    public boolean isState() { //Line data is received here
        return BoeBot.analogRead(this.pin) > 1500;
    }
}
