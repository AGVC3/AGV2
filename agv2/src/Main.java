import TI.BoeBot;
import TI.Servo;

public class Main {

    public static void main(String[] args) {

        Robot barrie = new Robot();

        while (true) {
            barrie.updateAll();
            BoeBot.wait(1);
        }
        /*Servo servo1 = new Servo(12);
        Servo servo2 = new Servo(13);

        servo1.start();
        servo2.start();

        while (true) {
            servo1.update(1500);
            servo2.update(1500);
            BoeBot.wait(10);
        }*/
    }
}
