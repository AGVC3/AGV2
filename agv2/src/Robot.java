import java.util.ArrayList;

public class Robot implements LineSensorCallback, UltrasoneSensorCallback, BluetoothModuleCallback {

    private ArrayList<Updatable> updatables;
    private ArrayList<String> routeInstructions;
    private Driver driver;
    private Notifications notifications;
    private LineFollowing lineFollowing;

    public Robot() {
        this.driver = new Driver();
        this.notifications = new Notifications(15, 6);
        this.lineFollowing = new LineFollowing(this.driver);
        this.routeInstructions = new ArrayList<>();

        this.updatables = new ArrayList<>();

        this.updatables.add(new LineSensor(this));
        this.updatables.add(new UltrasoneSensor(this));
        this.updatables.add(new RemoteControl());
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
            this.lineFollowing.straightForward();
        } else if (linesDetected.get(0) && !linesDetected.get(1) && !linesDetected.get(2)) { //turn to the left
            this.lineFollowing.turn("Right");
        } else if (!linesDetected.get(0) && !linesDetected.get(1) && linesDetected.get(2)) { //turn to the right
            this.lineFollowing.turn("Left");
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() > 1500) { //crossroads logic
            this.lineFollowing.crossRoadStopAndDriveBackwards();
        } else if (linesDetected.get(0) && linesDetected.get(1) && linesDetected.get(2) && this.driver.getLeft().getSpeed() < 1500) {
            this.lineFollowing.turn(this.lineFollowing.getInstructions().get(this.lineFollowing.getInstructions().size() - 1));
            this.lineFollowing.getInstructions().remove(this.lineFollowing.getInstructions().size() - 1);
        } else {
            this.driver.emergencyBreak();
        }
    }

    public void ultrasoneDetect(int pulse) {
        System.out.println(pulse);
        if (pulse > 17 && pulse < 500) {
            this.driver.goToSpeed(1400);
            this.notifications.noiseDrivingBackwards();
        } else if (pulse > 500 && pulse < 1000) {
            this.driver.emergencyBreak();
            this.notifications.ledOn();
            this.notifications.truckHorn();
        } else {
            this.driver.goToSpeed(1600);
        }
    }

    public void bluetoothDetect(char character) {

    }
}
