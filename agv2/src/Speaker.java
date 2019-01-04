import TI.*;

public class Speaker {
    private int pin;

    public Speaker(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return this.pin;
    }

    public void updateSpeaker() {
        Timer t1 = new Timer(500);
        Timer t2 = new Timer(1000);
        PWM pwm = new PWM(this.pin, 254);
        while (true) {
            if (t1.timeout()) {
                pwm.start();
            }
            if (t2.timeout()) {
                pwm.stop();
            }
        }
    }
}