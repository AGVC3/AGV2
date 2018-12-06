import TI.BoeBot;

public class Main {

    public static void main(String[] args) {

        Robot barrie = new Robot();

        while (true) {
            barrie.updateAll();
            BoeBot.wait(10000);
        }
    }
}
