package GUI;

import jssc.SerialPort;

public class BluetoothTransmitter {

    private SerialPort serialPort;
    private String route;

    public BluetoothTransmitter() {
        this.serialPort = new SerialPort("COM5");
        this.route = "";
    }

    public void transmitRoute() { //Deze methode verstuurd de route naar de BoeBot
        try {
            this.serialPort.openPort();
            this.serialPort.setParams(115200, 8, 1, 0);
            this.serialPort.writeBytes(this.route.toLowerCase().getBytes());
            this.serialPort.closePort();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void transmitEmergency() { //Deze methode verstuurt een emergency break
        try {
            this.serialPort.openPort();
            this.serialPort.setParams(115200, 8, 1, 0);
            this.serialPort.writeBytes("e".getBytes());
            this.serialPort.closePort();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}
