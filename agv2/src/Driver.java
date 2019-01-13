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

    public void turnWhileDriving(String turnDirection) { //Deze methode wordt gebruikt om te sturen als de BoeBot een lijn volgt
        if (turnDirection.equals("Left")) {
            this.left.setTargetSpeed(1620); //1620
            this.right.setTargetSpeed(1510); //1520
        } else if (turnDirection.equals("Right")) {
            this.right.setTargetSpeed(1620); //1620
            this.left.setTargetSpeed(1510); //1520
        }
    }

    public void turnSharp(String turnDirection) { //Deze methode draait de BoeBot op een kruispunt
        if (turnDirection.equals("Left")) {
            this.right.setTargetSpeed(1590);
            this.left.setTargetSpeed(1470);
        } else if (turnDirection.equals("Right")) {
            this.left.setTargetSpeed(1590);
            this.right.setTargetSpeed(1470);
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
