public class RemoteControl {

    private Driver driver;
    private LineSensorControl lineSensorControl;

    public RemoteControl(Driver driver, LineSensorControl lineSensorControl) {
        this.driver = driver;
        this.lineSensorControl = lineSensorControl;
    }

    public void dataToAction(String string) {
        switch (string) {
            case "000010010000"://UP
                this.driver.goToSpeed(1550);
                break;
            case "000010010001"://DOWN
                this.driver.goToSpeed(1450);
                break;
            case "000010010011"://LEFT
                this.driver.turn("Left");
                break;
            case "000010010010"://RIGHT
                this.driver.turn("Right");
                break;
            case "000010010100"://MUTE
                this.driver.goToSpeed(1500);
                break;
            case "000010010101"://POWER
                this.driver.emergencyBreak();
                break;
            case "temp":
                this.lineSensorControl.setOverride(!this.lineSensorControl.isOverride());
            default:
                break;
        }
    }


}
