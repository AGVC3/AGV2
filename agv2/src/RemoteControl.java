public class RemoteControl {

    private Driver driver;

    public RemoteControl(Driver driver) {
        this.driver = driver;
    }

    public void dataToAction(char character) {
        switch (character) {
            case 'w':
                this.driver.goToSpeed(1550);
                break;
            case 's':
                this.driver.goToSpeed(1450);
                break;
            case 'a':
                this.driver.turn("Left");
                break;
            case 'd':
                this.driver.turn("Right");
                break;
            case 'f':
                this.driver.goToSpeed(1500);
                break;
            case 'g':
                this.driver.emergencyBreak();
                break;
            default:
                break;
        }
    }


}
