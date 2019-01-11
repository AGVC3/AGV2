import TI.BoeBot;

public class LED {

    private int pin;

    public LED (int pin) {
        this.pin = pin;
    }

    public void updateColor(int r, int g, int b) {
        BoeBot.rgbSet(this.pin, r, g, b);
    }
}
