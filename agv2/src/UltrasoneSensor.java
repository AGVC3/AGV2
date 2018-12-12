import TI.BoeBot;

public class UltrasoneSensor implements Updatable {

    private UltrasoneSensorCallback callback;

    public UltrasoneSensor(UltrasoneSensorCallback callback) {
        this.callback = callback;
    }

    public void update() {
        BoeBot.digitalWrite(10, true);
        BoeBot.wait(0,500);
        BoeBot.digitalWrite(10, false);

        int pulse = BoeBot.pulseIn(11, true, 10000);
        this.callback.ultrasoneDetect(pulse);
    }
}
