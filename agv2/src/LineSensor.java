import TI.BoeBot;

import java.util.ArrayList;

public class LineSensor implements Updatable {

    private LineSensorCallback callback;
    private boolean state;

    public LineSensor(LineSensorCallback callback) {
        this.callback = callback;
        this.state = true;
    }

    public void update() {
        ArrayList<Boolean> linesDetected = new ArrayList<>();

        if (BoeBot.analogRead(0) > 1000) {
            linesDetected.add(true);
        } else {
            linesDetected.add(false);
        }
        if (BoeBot.analogRead(1) > 1000) {
            linesDetected.add(true);
        } else {
            linesDetected.add(false);
        }
        if (BoeBot.analogRead(2) > 1000) {
            linesDetected.add(true);
        } else {
            linesDetected.add(false);
        }

        if (state) {
            this.callback.lineSensorDetect(linesDetected);
        }
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
