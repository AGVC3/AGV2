import TI.Servo;
import TI.Timer;

public class Wheel implements Updatable {

    private int speed;
    private int targetSpeed;
    private String direction;
    private Servo wheel;
    private Timer timer;

    public Wheel(int pin, String direction) {
        this.wheel = new Servo(pin);
        this.speed = 1500;
        this.targetSpeed = 1500;
        this.direction = direction;
        this.timer = new Timer(5);
    }

    public void update() {
        if (this.timer.timeout()) {
            int accelaration = 10;
            if (this.speed != this.targetSpeed) {
                if ((Math.abs(this.speed - this.targetSpeed) > accelaration)) {
                    if (this.speed < this.targetSpeed) {
                        this.speed += accelaration;
                    } else {
                        this.speed -= accelaration;
                    }
                } else {
                    this.speed = this.targetSpeed;
                }
            }
            if (this.direction.equals("Left")) {
                this.wheel.update(this.speed);
            } else {
                this.wheel.update(3000 - this.speed);
            }
        }
    }

    public void setTargetSpeed(int targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public int getTargetSpeed() {
        return this.targetSpeed;
    }

    public void emergencyBreak() {
        this.targetSpeed = 1500;
        this.speed = 1500;
    }

    public int getSpeed() {
        return this.speed;
    }
}