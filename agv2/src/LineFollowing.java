import TI.Timer;


import javax.security.auth.callback.Callback;
import java.util.ArrayList;

public class LineFollowing implements Updatable {

    private Driver driver;
    private ArrayList<String> instructions;
    private String currentAction;
    private boolean isActive;
    private Timer timer;
    private LineFollowingCallback callback;

    public LineFollowing(Driver driver, LineFollowingCallback callback) {
        this.driver = driver;
        this.currentAction = "";
        this.timer = new Timer(1000);
        this.callback = callback;
        this.instructions = new ArrayList<>();
        this.instructions.add("D");
        this.instructions.add("R");
        this.instructions.add("F");
    }

    public void dataToAction() {
        try {
            this.currentAction = this.instructions.get(this.instructions.size() - 1);
            this.instructions.remove(this.instructions.size() - 1);
            System.out.println("current action: " + currentAction);
        } catch (Exception e) {
            //System.out.println("Exception");
        }

    }

    @Override
    public void update() {
        this.callback.lineFollowingLogic();
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
