import TI.Servo;

public class Wheel implements Updatable{

    private int speed;
    private int targetSpeed;
    private String direction;

    private Servo wheel;

    public Wheel (int pin, String direction) {
        this.wheel = new Servo(pin);
        this.speed = 1500;
        this.targetSpeed = 1500;
        this.direction = direction;

    }

    @Override
    public void update() {
        if (this.speed != this.targetSpeed) {
            if (this.speed < this.targetSpeed) {
                this.speed += 10;
            } else {
                this.speed -= 10;
            }
        }
        if (this.direction.equals("Right")) {
            this.wheel.update(this.speed);
        } else {
            this.wheel.update(3000 - this.speed);
        }
    }

    public void setTargetSpeed(int targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public void emergencyBreak (){
        this.wheel.update(1500);
    }
}