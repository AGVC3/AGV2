import java.util.ArrayList;

public class LineFollowing {

    private Driver driver;
    private ArrayList<String> instructions;

    public LineFollowing(Driver driver) {
        this.driver = driver;
        this.instructions = new ArrayList<>();
        this.instructions.add("Right");
        this.instructions.add("Left");
        this.instructions.add("Right");
    }

    public void straightForward() {
        this.driver.goToSpeed(1600);
    }

    public void turn(String direction) {
        this.driver.goToSpeed(1500);
        this.driver.turnSharp(direction);
    }

    public void crossRoadStopAndDriveBackwards() {
        this.driver.goToSpeed(1450);
    }

    public ArrayList<String> getInstructions() {
        return this.instructions;
    }
}
