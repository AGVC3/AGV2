import TI.Timer;

public class Driver {

    private Wheel left;
    private Wheel right;

    public Driver() {
        this.left = new Wheel(12, "Left");
        this.right = new Wheel(13, "Right");
    }

    public void goToSpeed(int targetSpeed) {
        this.left.setTargetSpeed(targetSpeed);
        this.right.setTargetSpeed(targetSpeed);
    }

    public void turn(String turnDirection) {
        if (turnDirection.equals("Left")) {
            this.right.setTargetSpeed(1600);
        } else if (turnDirection.equals("Right")) {
            this.left.setTargetSpeed(1600);
        }
    }

    public void turnWhileDriving(String turnDirection) {
        if (turnDirection.equals("Left")) {
            this.left.setTargetSpeed(1620); //1620
            this.right.setTargetSpeed(1520); //1520
        } else if (turnDirection.equals("Right")) {
            this.right.setTargetSpeed(1620); //1620
            this.left.setTargetSpeed(1520); //1520
        }
    }

    public void action(String actionString) {
        Timer timer = new Timer(100);
        if (actionString.equals("Right")) {
            while (true) {
                turnWhileDriving("Left");
                if (timer.timeout()) {
                    break;
                }
            }
        }
    }

    public void turnSharp(String turnDirection) {
        if (turnDirection.equals("Left")) {
            this.right.setTargetSpeed(1550);
            this.left.setTargetSpeed(1450);
        } else if (turnDirection.equals("Right")) {
            this.left.setTargetSpeed(1550);
            this.right.setTargetSpeed(1450);
        }
    }

    public void emergencyBreak() {
        this.left.emergencyBreak();
        this.right.emergencyBreak();
    }

    public Wheel getLeft() {
        return this.left;
    }

    public Wheel getRight() {
        return this.right;
    }

    public int getSpeed() {
        return this.left.getSpeed();
    }
}
