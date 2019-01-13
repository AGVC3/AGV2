import java.util.ArrayList;

public class Robot implements LineSensorCallback, UltrasoneSensorCallback, BluetoothModuleCallback, RoutePlannerCallback, InfraredModuleCallback {

    private ArrayList<Updatable> updatables;
    private Driver driver;
    private Notifications notifications;
    private RoutePlanner routePlanner;
    private RemoteControl remoteControl;
    private LineSensorControl lineSensorControl;

    public Robot() {
        this.driver = new Driver();
        this.notifications = new Notifications();
        this.routePlanner = new RoutePlanner(this.driver, this);
        this.lineSensorControl = new LineSensorControl(this, this.driver);
        this.remoteControl = new RemoteControl(this.driver, this.lineSensorControl, this.notifications);

        this.updatables = new ArrayList<>();
        this.updatables.add(this.routePlanner);
        this.updatables.add(this.lineSensorControl);
        this.updatables.add(new UltrasoneSensor(this));
        this.updatables.add(new BluetoothModule(this));
        this.updatables.add(new InfraredModule(this));
        this.updatables.add(this.driver.getLeft());
        this.updatables.add(this.driver.getRight());
        this.updatables.add(this.notifications.getLedControl());
    }

    public void updateAll() {
        for (Updatable u : this.updatables) {
            u.update();
        }
    }

    public void lineSensorDetect(ArrayList<Boolean> linesDetected) {
        if (this.lineSensorControl.isStop()) {}
        if (!linesDetected.get(0) && linesDetected.get(1) && !linesDetected.get(2)) { //straight forward
            this.driver.goToSpeed(1550); //1550
            this.notifications.ledOn("Blue");
        } else if (linesDetected.get(0) && !linesDetected.get(1) && !linesDetected.get(2)) { //turn to the left
            this.driver.turnWhileDriving("Right");
        } else if (!linesDetected.get(0) && !linesDetected.get(1) && linesDetected.get(2)) { //turn to the right
            this.driver.turnWhileDriving("Left");
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() > 1500) { //crossroads logic
            this.driver.goToSpeed(1500);
            this.lineSensorControl.setState(false);
            this.lineSensorControl.getTimerLine().setInterval(600);
            this.routePlanner.dataToAction();
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() <= 1500) {
//            this.driver.goToSpeed(1500);
//            this.lineSensorControl.setState(false);
//            this.routePlanner.dataToAction();
        }
    }

    public void routePlannerLogic() {
        if (this.routePlanner.getCurrentAction().equals("")) {
            return;
        }
        if (!this.lineSensorControl.getState()) {                                                                       //When an action is present but the linefollowers are still on
            if (this.routePlanner.getCurrentAction().equals("R")) {                                                     //Do whatever action is necessary
                this.driver.turnSharp("Right");
            } else if (this.routePlanner.getCurrentAction().equals("L")) {
                this.driver.turnSharp("Left");
            } else if (this.routePlanner.getCurrentAction().equals("F")) {
                this.lineSensorControl.getTimerStop2().setInterval(250);
                this.lineSensorControl.setStop2(true);
                this.driver.goToSpeed(1550);
            } else if (this.routePlanner.getCurrentAction().equals("S")) {
                this.lineSensorControl.setStop(true);
                this.lineSensorControl.getTimerStop().setInterval(5000);
                this.notifications.ledOn("Green");
            } else if (this.routePlanner.getCurrentAction().equals("D")) {
                this.notifications.ledOn("Green");
            }
        }
    }

    public void ultrasoneDetect(int pulse) {                                                                            //Hier wordt de data van de ultrasoonsensoren verwerkt tot acties
        if (pulse > 17 && pulse < 100) {
            this.driver.emergencyBreak();
            this.notifications.ledOn("Red");
        } else if (pulse >= 100 && pulse < 500) {
            this.driver.goToSpeed(1450);
            this.notifications.ledOn("Red");
        } else if (pulse >= 500 && pulse < 1000) {
            if (this.driver.getSpeed() >= 1500) {
                this.driver.goToSpeed(1500);
            }
        } else if (pulse >= 1000) {
            if (this.notifications.getLedControl().getColor().equals("Red")) {
                if (this.lineSensorControl.isOverride()) {
                    this.notifications.ledOn("Pink");
                } else {
                    this.notifications.ledOn("Blue");
                }
            }
        }
    }

    public void bluetoothDetect(char character) {
        this.notifications.ledOn("Green");
        this.routePlanner.dataToInstruction(character);
    }

    public void infraredDetect(String binary) {
        this.remoteControl.dataToAction(binary);
    }
}
