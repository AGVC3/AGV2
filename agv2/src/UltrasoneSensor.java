import TI.BoeBot;
import TI.Timer;

public class UltrasoneSensor implements Updatable {

    private UltrasoneSensorCallback callback;
    private Timer timer;

    public UltrasoneSensor(UltrasoneSensorCallback callback) {
        this.callback = callback;
        this.timer = new Timer(100);
    }

    public void update() {
        if (this.timer.timeout()) {
            BoeBot.digitalWrite(2, true);
            BoeBot.wait(0, 500);
            BoeBot.digitalWrite(2, false);

            int pulse = BoeBot.pulseIn(3, true, 10000);
            this.callback.ultrasoneDetect(pulse);
        }
    }
}
