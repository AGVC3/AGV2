import TI.BoeBot;

import java.util.ArrayList;

public class LineSensor implements Updatable {

    private LineSensorCallback lineDetect;

    public LineSensor(LineSensorCallback lineDetect) {
        this.lineDetect = lineDetect;
    }

    public void update() {
        ArrayList<Integer> linesDetected = new ArrayList<>();
        if (BoeBot.analogRead(0) > 1000) {
            linesDetected.add(1);
        } else {
            linesDetected.add(0);
        }
        if (BoeBot.analogRead(1) > 1000) {
            linesDetected.add(1);
        } else {
            linesDetected.add(0);
        }
        if (BoeBot.analogRead(2) > 1000) {
            linesDetected.add(1);
        } else {
            linesDetected.add(0);
        }

        this.lineDetect.lineSensorDetect(linesDetected);
    }
}
