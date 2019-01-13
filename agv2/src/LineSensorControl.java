import TI.Timer;

import java.util.ArrayList;

public class LineSensorControl implements Updatable {

    private Timer timer = new Timer(10);
    private Timer timerLine = new Timer(600);
    private Timer timerStop = new Timer(5000);
    private Timer timerStop2 = new Timer(250);
    private boolean isState;
    private boolean override;
    private boolean isStop;
    private boolean isStop2;
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
        this.isState = true;
        this.override = false;
        this.isStop = false;
        this.isStop2 = false;
    }

    public void update() {
        if (this.isState && !this.override && !this.isStop && !this.isStop2) { //Hier wordt de data van de drie lijnsensoren opgehaald, verzameld en doorgestuurd
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
            this.isState = true;
        }
        if (timerStop.timeout()) {
            if (this.isStop) {
                this.isStop = false;
                this.isStop2 = true;
                this.timerStop2.setInterval(250);
                this.driver.goToSpeed(1550);
            }
        }
        if (this.timerStop2.timeout()) {
            this.isStop2 = false;
        }
    }

    public boolean getState() {
        return this.isState;
    }

    public void setState(boolean isState) {
        this.isState = isState;
    }

    public boolean isOverride() {
        return this.override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isStop() {
        return this.isStop;
    }

    public void setStop(boolean stop) {
        this.isStop = stop;
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
        this.isStop2 = stop2;
    }
}
