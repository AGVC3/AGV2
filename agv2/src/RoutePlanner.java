import TI.Timer;
import java.util.ArrayList;

public class RoutePlanner implements Updatable {

    private Driver driver;
    private ArrayList<String> instructions;
    private String currentAction;
    private boolean isActive;
    private Timer timer;
    private RoutePlannerCallback callback;

    public RoutePlanner(Driver driver, RoutePlannerCallback callback) {
        this.driver = driver;
        this.currentAction = "";
        this.timer = new Timer(250);
        this.callback = callback;
        this.instructions = new ArrayList<>();
    }

    public void dataToAction() { //This method gets an action, and removes hit from the this.instructions arraylist
        try {
            this.currentAction = this.instructions.get(this.instructions.size() - 1);
            this.instructions.remove(this.instructions.size() - 1);
            System.out.println("current action: " + this.currentAction);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void dataToInstruction(char character) { //This method converts data of the BluetoothModule to an action in the this.instructions arraylist
        switch (character) {
            case 'f':
                this.instructions.add("F");
                break;
            case 's':
                this.instructions.add("S");
                break;
            case 'l':
                this.instructions.add("L");
                break;
            case 'd':
                this.instructions.add("D");
                break;
            case 'r':
                this.instructions.add("R");
                break;
            case 'e':
                this.driver.emergencyBreak();
            default:
                break;
        }
    }

    @Override
    public void update() {
        if (this.timer.timeout()) {
            this.callback.routePlannerLogic();
        }
    }

    public String getCurrentAction() {
        return this.currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
