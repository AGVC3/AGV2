import TI.BoeBot;
import TI.Timer;

public class InfraredModule implements Updatable {

    private int pin;
    private Timer timer;
    private InfraredModuleCallback callback;

    public InfraredModule (InfraredModuleCallback callback) {
        this.callback = callback;
        this.pin = 7;
        this.timer = new Timer(25);
    }

    public void update() {
        if (this.timer.timeout()) { //here is the infrared being received
            int pulse = BoeBot.pulseIn(this.pin, false, 6000);
            StringBuilder binaryCode = new StringBuilder();
            if (pulse > 2000) {
                int[] signal = new int[12];

                for (int i = 0; i < 12; i++) {
                    signal[i] = BoeBot.pulseIn(this.pin, false, 20000);
                }

                for (int i = 0; i < 12; i++) {
                    binaryCode.append(signal[i] < 800 ? "0" : "1");
                }

                binaryCode.reverse();
                this.callback.infraredDetect(binaryCode.toString());
            } else {
                this.callback.infraredDetect(binaryCode.toString());
            }
        }
    }
}
