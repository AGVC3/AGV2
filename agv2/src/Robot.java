import java.util.ArrayList;

public class Robot implements LineSensorCallback, UltrasoneSensorCallback, RemoteControlCallback {

    private ArrayList<Updatable> updatables;
    private Driver driver;

    public Robot() {
        this.updatables = new ArrayList<>();

        this.updatables.add(new LineSensor(this));
        this.updatables.add(new UltrasoneSensor(this));
        this.updatables.add(new RemoteControl());
        this.updatables.add();

        this.driver = new Driver();
    }

    public void updateAll() {
        for (Updatable u : this.updatables) {
            u.update();
        }
    }

    public void lineSensorDetect(ArrayList<Integer> linesDetected) {
        System.out.println(linesDetected);
        //plaats hier lijnsensor logica
    }

    public void ultrasoneDetect(int pulse) {
        System.out.println(pulse);
        if (pulse > 17 && pulse < 1000) {
            this.driver.emergencyBreak();
        }
    }
}
