public class RemoteControl {

    private Driver driver;
    private LineSensorControl lineSensorControl;
    private Notifications notifications;

    public RemoteControl(Driver driver, LineSensorControl lineSensorControl, Notifications notifications) {
        this.driver = driver;
        this.lineSensorControl = lineSensorControl;
        this.notifications = notifications;
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
                this.notifications.ledOn("Purple");
                break;
            case "000010000000": //Number 1
                this.lineSensorControl.setOverride(!this.lineSensorControl.isOverride());
                if (this.lineSensorControl.isOverride()) {
                    this.driver.goToSpeed(1500);
                    this.notifications.ledOn("Pink");
                } else {
                    this.notifications.ledOn("Blue");
                }
                break;
            default:
                break;
        }
    }


}
