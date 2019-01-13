import TI.BoeBot;
import TI.Timer;
import java.util.ArrayList;

public class LEDControl implements Updatable {

    private ArrayList<LED> leds;
    private Timer timer;
    private String color;

    public LEDControl () { // initializes the LED (value of pin must be between 0-5)
        this.color = "";
        this.leds = new ArrayList<>();
        this.leds.add(new LED(0));
        this.leds.add(new LED(1));
        this.leds.add(new LED(2));
        this.leds.add(new LED(3));
        this.leds.add(new LED(4));
        this.leds.add(new LED(5));
        this.timer = new Timer(100);
    }

    public void update() {
        if (this.timer.timeout()) {
            BoeBot.rgbShow();
        }
    }

    public void setAllLedPinsColor(String color) { //Hier wordt door alle ledjes heengelopen en worden ze de goede kleur gemaakt.
        switch (color) {
            case "Red":
                this.color = "Red";
                for (LED led : this.leds) {
                    led.updateColor(50,0,0);
                }
                break;
            case "Green":
                this.color = "Green";
                for (LED led : this.leds) {
                    led.updateColor(0,10,0);
                }
                break;
            case "Blue":
                this.color = "Blue";
                for (LED led : this.leds) {
                    led.updateColor(0,0,10);
                }
                break;
            case "Pink":
                this.color = "Pink";
                for (LED led : this.leds) {
                    led.updateColor(50,0,10);
                }
        }
    }

    public void pinsAllOff () {
        for (LED led : this.leds){
            led.updateColor( 0,0,0);
        }
    }

    public String getColor() {
        return this.color;
    }
}