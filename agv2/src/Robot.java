import java.util.ArrayList;

public class Robot {

    private ArrayList<Updatable> sensors;

    public Robot() {
        this.sensors = new ArrayList<>();
        this.sensors.add(new LineSensor());
    }

    public void updateAll() {
        for (Updatable u : this.sensors) {
            u.update();
        }
    }
}
