package GUI;

import jssc.SerialPort;

public class BluetoothTransmitter {

    private SerialPort serialPort;
    private String route;

    public BluetoothTransmitter() {
        this.serialPort = new SerialPort("COM5");
        this.route = "";
    }

    public void transmitRoute() { //This method sends the route to the BoeBot
        try {
            this.serialPort.openPort();
            this.serialPort.setParams(115200, 8, 1, 0);
            this.serialPort.writeBytes(this.route.toLowerCase().getBytes());
            this.serialPort.closePort();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void transmitEmergency() { //This method sends a emergency break command to the BoeBot
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
