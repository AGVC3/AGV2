import TI.Timer;

import java.util.ArrayList;

public class LineSensorControl implements Updatable {

    private Timer timer = new Timer(10);
    private Timer timerLine = new Timer(600);
    private Timer timerStop = new Timer(5000);
    private Timer timerStop2 = new Timer(250);
    private boolean state;
    private boolean override;
    private boolean stop;
    private boolean stop2;
    private LineSensor leftSensor;
    private LineSensor middleSensor;
    private LineSensor rightSensor;
    private LineSensorCallback callback;
    private Driver driver;

    public LineSensorControl(LineSensorCallback callback, Driver driver) {
        this.leftSensor = new LineSensor(2);
        this.middleSensor = new LineSensor(1);
        this.rightSensor = new LineSensor(0);
        this.driver = driver;
        this.callback = callback;
        this.state = true;
        this.override = false;
        this.stop = false;
        this.stop2 = false;
    }

    public void update() {
        if (state && !override && !stop && !stop2) {
            if (timer.timeout()) {
                ArrayList<Boolean> linesDetected = new ArrayList<>();
                linesDetected.add(leftSensor.isState());
                linesDetected.add(middleSensor.isState());
                linesDetected.add(rightSensor.isState());
//                System.out.println(linesDetected);
                this.callback.lineSensorDetect(linesDetected);
            }
        }
        if (timerLine.timeout()) {
            this.state = true;
        }
        if (timerStop.timeout()) {
            if (this.stop) {
                this.stop = false;
                this.stop2 = true;
                this.timerStop2.setInterval(250);
                this.driver.goToSpeed(1550);
            }
        }
        if (timerStop2.timeout()) {
            this.stop2 = false;
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

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Timer getTimerLine() {
        return timerLine;
    }

    public Timer getTimerStop() {
        return timerStop;
    }

    public Timer getTimerStop2() {
        return timerStop2;
    }

    public void setStop2(boolean stop2) {
        this.stop2 = stop2;
    }
}
