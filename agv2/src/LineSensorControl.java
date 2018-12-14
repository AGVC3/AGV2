import TI.BoeBot;
import TI.Timer;

import java.util.ArrayList;

public class LineSensorControl implements Updatable {

    Timer timer = new Timer(50);
    private boolean state;
    private LineSensor leftSensor;
    private LineSensor middleSensor;
    private LineSensor rightSensor;
    private LineSensorCallback callback;

    public LineSensorControl(LineSensorCallback callback) {
        this.leftSensor = new LineSensor(0);
        this.middleSensor = new LineSensor(1);
        this.rightSensor = new LineSensor(2);
        this.callback = callback;
        this.state = true;
    }

    public void update() {
        if (state) {
            if (timer.timeout()) {
                ArrayList<Boolean> linesDetected = new ArrayList<>();
                linesDetected.add(leftSensor.isState());
                linesDetected.add(middleSensor.isState());
                linesDetected.add(rightSensor.isState());
                this.callback.lineSensorDetect(linesDetected);
            }
        }
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
