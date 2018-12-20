import TI.BoeBot;
import java.util.ArrayList;


public class LineSensor{

    private int pin;

    public LineSensor (int pin) {
        this.pin = pin;
    }


    public void update() {
        ArrayList<Boolean> linesDetected = new ArrayList<>();

        if (BoeBot.analogRead(2) > 1500) {
            //System.out.println(BoeBot.analogRead(0));
            linesDetected.add(true);
        } else {
            //System.out.println(BoeBot.analogRead(0));
            linesDetected.add(false);
        }
        if (BoeBot.analogRead(1) > 1500) {
            //System.out.println(BoeBot.analogRead(1));
            linesDetected.add(true);
        } else {
            //System.out.println(BoeBot.analogRead(1));
            linesDetected.add(false);
        }
        if (BoeBot.analogRead(0) > 1500) {
            //System.out.println(BoeBot.analogRead(2));
            linesDetected.add(true);
        } else {
            //System.out.println(BoeBot.analogRead(2));
            linesDetected.add(false);
        }

        if (state) {
            this.callback.lineSensorDetect(linesDetected);
        }}

    public boolean isState() {
        if (BoeBot.analogRead(pin) > 1500) {
            return true;
        } else {
            return false;
        }
    }
}
