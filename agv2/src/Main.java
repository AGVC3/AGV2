import TI.BoeBot;
import TI.SerialConnection;
import TI.Servo;
import jssc.SerialPort;

public class Main {

    public static void main(String[] args) {

        Robot barrie = new Robot();

        while (true) {
            barrie.updateAll();
            BoeBot.wait(1);
        }
    }
}
