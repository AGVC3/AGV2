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
}
