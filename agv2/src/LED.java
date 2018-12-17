import TI.BoeBot;
import TI.Timer;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;


public class LED {

    private ArrayList<Integer> ledPins;

    public LED () { // initializes the LED (value of pin must be between 0-5)
        this.ledPins = new ArrayList<>();
    }

    public void setColorOfPinToRed(int pin) { //set the color to red
        BoeBot.rgbSet(this.ledPins.get(pin), 50, 0, 0);

    }

    public void setColorofPinToBlue(int pin) { //set the color to blue
        BoeBot.rgbSet(this.ledPins.get(pin), 0, 0, 10);

    }

    public void setColorOfPinToGreen(int pin) {  // set the color to green
        BoeBot.rgbSet(this.ledPins.get(pin), 0, 10, 0);

    }

    public void addAllPinsToList () {

        this.ledPins.add(0);
        this.ledPins.add(1);
        this.ledPins.add(2);
        this.ledPins.add(3);
        this.ledPins.add(4);
        this.ledPins.add(5);

    }

    public void addPinList ( int pin ) {
        if (this.ledPins.contains(pin)){

        } else {
            this.ledPins.add(pin);
        }
    }

    public void setColorOfPin(int pin, int redTone, int greenTone, int blueTone) { //set this.pin (0-5) to a set color
        BoeBot.rgbSet(pin, redTone, greenTone, blueTone); //Tone must be between values of int 0 - 255

    }

    public void activateColors() { // is used to initialize the colors on the BoeBot
        BoeBot.rgbShow();
    }

    public void setAllLedPinsColorRed() {
        for ( int i = 0 ; i <= 5 ; i++ ) {
            BoeBot.rgbSet(i, 50,0,0);
        }
    }

    public void setAllLedPinsColorBlue() {
        for ( int i = 0 ; i <= 5 ; i++ ) {
            BoeBot.rgbSet(i, 0, 0, 10);
        }
    }

    public void setAllLedPinsColorGreen() {
        for ( int i = 0 ; i <= 5 ; i++ ) {
            BoeBot.rgbSet(i, 0, 10, 0);
        }
    }

    public void setGroupToColorRed() {
        for (Integer pins : this.ledPins) {
            BoeBot.rgbSet(pins, 50, 0, 0);
        }
    }

    public void setGroupToColorBlue() {
        for (Integer pins : this.ledPins) {
            BoeBot.rgbSet(pins, 0, 0, 10);

        }
    }

    public void setGroupToColorGreen () {
        for (Integer pins : this.ledPins) {
            BoeBot.rgbSet(pins, 0, 10, 0);
        }
    }

    public void pinsAllOff () {
        for (Integer pins : this.ledPins){
            BoeBot.rgbSet(pins, 0,0,0);
        }
    }
}