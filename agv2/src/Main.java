import TI.BoeBot;

public class Main {

    public static void main(String[] args) {

        Robot barrie = new Robot(); //Barrie Bokworst

        while (true) {
            barrie.updateAll();
            BoeBot.wait(1);
        }
    }
}
