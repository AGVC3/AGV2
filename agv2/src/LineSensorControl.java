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
        if (this.state && !this.override && !this.stop && !this.stop2) {
            if (this.timer.timeout()) {
                ArrayList<Boolean> linesDetected = new ArrayList<>();
                linesDetected.add(this.leftSensor.isState());
                linesDetected.add(this.middleSensor.isState());
                linesDetected.add(this.rightSensor.isState());
//                System.out.println(linesDetected);
                this.callback.lineSensorDetect(linesDetected);
            }
        }
        if (this.timerLine.timeout()) {
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
        if (this.timerStop2.timeout()) {
            this.stop2 = false;
        }
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isOverride() {
        return this.override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isStop() {
        return this.stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Timer getTimerLine() {
        return this.timerLine;
    }

    public Timer getTimerStop() {
        return this.timerStop;
    }

    public Timer getTimerStop2() {
        return this.timerStop2;
    }

    public void setStop2(boolean stop2) {
        this.stop2 = stop2;
    }
}
