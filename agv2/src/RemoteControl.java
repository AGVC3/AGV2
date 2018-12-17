public class RemoteControl {

    private Driver driver;
    private LineSensorControl lineSensorControl;

    public RemoteControl(Driver driver, LineSensorControl lineSensorControl) {
        this.driver = driver;
        this.lineSensorControl = lineSensorControl;
    }

    public void dataToAction(char character) {
        switch (character) {
            case 'w':
                this.driver.goToSpeed(1700);
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
            case 'h':
                this.lineSensorControl.setOverride(!this.lineSensorControl.isOverride());
            default:
                break;
        }
    }
}
