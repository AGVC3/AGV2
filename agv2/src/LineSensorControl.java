import TI.BoeBot;
import TI.Timer;

import java.util.ArrayList;

public class LineSensorControl implements Updatable {

    Timer timer = new Timer(5);
    Timer timerLine = new Timer(600);
    private boolean state;
    private boolean override;
    private LineSensor leftSensor;
    private LineSensor middleSensor;
    private LineSensor rightSensor;
    private LineSensorCallback callback;

    public LineSensorControl(LineSensorCallback callback) {
        this.leftSensor = new LineSensor(2);
        this.middleSensor = new LineSensor(1);
        this.rightSensor = new LineSensor(0);
        this.callback = callback;
        this.state = true;
        this.override = false;
    }

    public void update() {
        if (state && !override) {
            if (timer.timeout()) {
                ArrayList<Boolean> linesDetected = new ArrayList<>();
                linesDetected.add(leftSensor.isState());
                linesDetected.add(middleSensor.isState());
                linesDetected.add(rightSensor.isState());
                this.callback.lineSensorDetect(linesDetected);
            }
        }
        if (timerLine.timeout()) {
            this.state = true;
        }
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }
}
