import TI.BoeBot;

import java.util.ArrayList;


public class LEDControl implements Updatable {

    private ArrayList<LED> leds;

    public LEDControl () { // initializes the LED (value of pin must be between 0-5)
        this.leds = new ArrayList<>();
        this.leds.add(new LED(0));
        this.leds.add(new LED(1));
        this.leds.add(new LED(2));
        this.leds.add(new LED(3));
        this.leds.add(new LED(4));
        this.leds.add(new LED(5));

    }

    @Override
    public void update() {
        BoeBot.rgbShow();
    }

    public void setAllLedPinsColor(String color) {
        switch (color) {
            case "Red":
                for (LED led : this.leds) {
                    led.updateColor(50,0,0);
                }
                break;
            case "Green":
                for (LED led : this.leds) {
                    led.updateColor(0,10,0);
                }
                break;
            case "Blue":
                for (LED led : this.leds) {
                    led.updateColor(0,0,10);
                }
                break;
        }
    }

    public void pinsAllOff () {
        for (LED led : this.leds){
            led.updateColor( 0,0,0);
        }
    }


}