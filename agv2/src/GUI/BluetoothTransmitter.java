package GUI;

import TI.BoeBot;
import jssc.SerialPort;
import jssc.SerialPortException;

public class BluetoothTransmitter {

    private SerialPort serialPort;
    private String route;

    public BluetoothTransmitter() {
        this.serialPort = new SerialPort("COM5");
        this.route = "";
    }

    public void transmitRoute() {
        try {
            serialPort.openPort();

            serialPort.setParams(115200, 8, 1, 0);

            serialPort.writeBytes(route.toLowerCase().getBytes());

            serialPort.closePort();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void transmitEmergency() {
        try {
            serialPort.openPort();

            serialPort.setParams(115200, 8, 1, 0);

            serialPort.writeBytes("e".getBytes());

            serialPort.closePort();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
