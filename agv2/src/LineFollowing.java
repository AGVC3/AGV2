import TI.Timer;

import java.util.ArrayList;

public class LineFollowing implements Updatable{

    private Driver driver;
    private ArrayList<String> instructions;
    private String currentAction;
    private boolean isActive;
    private Timer timer;

    public LineFollowing(Driver driver) {
        this.driver = driver;
        this.currentAction = "";
        this.timer = new Timer(300);
        this.instructions = new ArrayList<>();
        this.instructions.add("R");
        this.instructions.add("L");
        this.instructions.add("F");
        this.instructions.add("D");
    }

    public void dataToAction(){
        this.currentAction = this.instructions.get(this.instructions.size()-1);
        this.instructions.remove(this.instructions.size()-1);
    }

    @Override
    public void update() {
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
