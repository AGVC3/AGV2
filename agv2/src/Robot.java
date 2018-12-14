import TI.BoeBot;

import java.util.ArrayList;

public class Robot implements LineSensorCallback, UltrasoneSensorCallback, BluetoothModuleCallback {

    private ArrayList<Updatable> updatables;
    private ArrayList<String> routeInstructions;
    private Driver driver;
    private Notifications notifications;
    private LineFollowing lineFollowing;
    private RemoteControl remoteControl;
    private LineSensorControl lineSensorControl;

    public Robot() {
        this.driver = new Driver();
        this.notifications = new Notifications(15, 6);
        this.lineFollowing = new LineFollowing(this.driver);
        this.remoteControl = new RemoteControl(this.driver);
        this.lineSensorControl = new LineSensorControl(this);
        this.routeInstructions = new ArrayList<>();

        this.updatables = new ArrayList<>();
        this.updatables.add(lineSensorControl);
        this.updatables.add(new UltrasoneSensor(this));
        this.updatables.add(new BluetoothModule(this));
        this.updatables.add(this.driver.getLeft());
        this.updatables.add(this.driver.getRight());
    }

    public void updateAll() {
        for (Updatable u : this.updatables) {
            u.update();
        }
    }

    public void lineSensorDetect(ArrayList<Boolean> linesDetected) {
        //System.out.println(linesDetected);
        if (!linesDetected.get(0) && linesDetected.get(1) && !linesDetected.get(2)) { //straight forward
            this.driver.goToSpeed(1550);
        } else if (linesDetected.get(0) && !linesDetected.get(1) && !linesDetected.get(2)) { //turn to the left
            this.driver.turnWhileDriving("Right");
        } else if (!linesDetected.get(0) && !linesDetected.get(1) && linesDetected.get(2)) { //turn to the right
            this.driver.turnWhileDriving("Left");
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() > 1500) { //crossroads logic
            this.driver.goToSpeed(1450);
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() <= 1500) {
            this.driver.emergencyBreak();

        }
    }

    public void lineFollowingLogic() {
        if (this.lineFollowing.getCurrentAction().isEmpty()) {
            return;
        }

        if (this.lineFollowing.getTimer().timeout() && this.lineSensorControl.getState() == true) {                     //When an action is present but the linefollowers are still on

            this.lineSensorControl.setState(false);                                                                     //Turn off the linesensors

            if (this.lineFollowing.getCurrentAction().equals("R")) {                                                    //Do whatever action is necessary
                this.driver.turn("Right");
            } else if (this.lineFollowing.getCurrentAction().equals("L")) {
                this.driver.turn("Left");
            } else if (this.lineFollowing.getCurrentAction().equals("F")) {
                this.driver.goToSpeed(1550);
            } else if (this.lineFollowing.getCurrentAction().equals("D")) {
                this.notifications.ledOn();
                this.notifications.truckHorn();
            }
        } else if (this.lineFollowing.getTimer().timeout() && this.lineSensorControl.getState() == false) {             //If an action is present and the linefollowers are of, turn them on and clear the action
            this.lineSensorControl.setState(true);
            this.lineFollowing.setCurrentAction("");
        }
    }

    public void ultrasoneDetect(int pulse) {

        if (pulse > 17 && pulse < 300) {
            this.driver.goToSpeed(1490);

            System.out.println(pulse);

            this.notifications.noiseDrivingBackwards();
        } else if (pulse > 300 && pulse < 1000) {
            this.driver.goToSpeed(1490);
            System.out.println(pulse);

            this.notifications.ledOn();
            this.notifications.truckHorn();
        } else {
            this.notifications.ledOff();
        }
    }

    public void bluetoothDetect(char character) {
        System.out.println(character);
        this.remoteControl.dataToAction(character);
    }
}
