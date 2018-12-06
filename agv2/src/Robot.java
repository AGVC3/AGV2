import java.util.ArrayList;

public class Robot implements LineSensorCallback, UltrasoneSensorCallback, RemoteControlCallback {

    private ArrayList<Updatable> sensors;

    public Robot() {
        this.sensors = new ArrayList<>();
        this.sensors.add(new LineSensor(this));
        this.sensors.add(new UltrasoneSensor(this));
        this.sensors.add(new RemoteControl());
    }

    public void updateAll() {
        for (Updatable u : this.sensors) {
            u.update();
        }
    }

    public void lineSensorDetect(ArrayList<Integer> linesDetected) {
        System.out.println(linesDetected);
    }

    public void ultrasoneDetect(int pulse) {
        System.out.println(pulse);
    }
}
