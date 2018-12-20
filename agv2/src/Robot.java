import java.util.ArrayList;

public class Robot implements LineSensorCallback, UltrasoneSensorCallback, BluetoothModuleCallback, LineFollowingCallback {

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
        this.lineFollowing = new LineFollowing(this.driver, this);
        this.lineSensorControl = new LineSensorControl(this);
        this.remoteControl = new RemoteControl(this.driver, lineSensorControl);
        this.routeInstructions = new ArrayList<>();

        this.updatables = new ArrayList<>();
        this.updatables.add(lineFollowing);
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
        System.out.println(linesDetected);
        if (!linesDetected.get(0) && linesDetected.get(1) && !linesDetected.get(2)) { //straight forward
            this.driver.goToSpeed(1550);
        } else if (linesDetected.get(0) && !linesDetected.get(1) && !linesDetected.get(2)) { //turn to the left
            this.driver.turnWhileDriving("Right");
        } else if (!linesDetected.get(0) && !linesDetected.get(1) && linesDetected.get(2)) { //turn to the right
            this.driver.turnWhileDriving("Left");
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() > 1500) { //crossroads logic
            this.driver.goToSpeed(1500);
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() <= 1500) {
            this.driver.goToSpeed(1500);
            this.lineSensorControl.setState(false);
            this.lineFollowing.dataToAction();
        }
    }

    public void lineFollowingLogic() {
        if (this.lineFollowing.getCurrentAction().equals("")) {
            return;
        }

        if (!this.lineSensorControl.getState()) {                                                                       //When an action is present but the linefollowers are still on

            if (this.lineFollowing.getCurrentAction().equals("R")) {                                                    //Do whatever action is necessary

                this.driver.turn("Right");

            } else if (this.lineFollowing.getCurrentAction().equals("L")) {

                this.driver.turn("Left");

            } else if (this.lineFollowing.getCurrentAction().equals("F")) {

                this.driver.goToSpeed(1550);

            } else if (this.lineFollowing.getCurrentAction().equals("D")) {

            }
        }
    }

    public void ultrasoneDetect(int pulse) {

        if (pulse > 17 && pulse < 100) {
            this.driver.emergencyBreak();
        } else if (pulse >= 100 && pulse < 500) {
            this.driver.goToSpeed(1450);
            System.out.println(pulse);
        } else if (pulse >= 500 && pulse < 1000) {

            if (this.driver.getSpeed() >= 1500) {
                this.driver.goToSpeed(1500);
                System.out.println(pulse);
            }
        }
    }

    public void bluetoothDetect(char character) {
        System.out.println(character);
        this.remoteControl.dataToAction(character);
    }
}
