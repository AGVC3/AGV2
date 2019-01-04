package GUI;
import jssc.SerialPort;
import jssc.SerialPortException;

public class BluetoothTransmitter {

    private SerialPort serialPort;

    public BluetoothTransmitter(){
        this.serialPort = new SerialPort("COM5");
    }

    public void transmitRoute() {
        try {
            serialPort.openPort();

            serialPort.setParams(115200,8,1,0);

            serialPort.writeInt(7);
            System.out.println("hoi");
//
//            byte[] buffer = serialPort.readBytes(10);
//            for (int i = 0; i < 10; i++) {
//                System.out.println(buffer[i] + "-");
//            }

            serialPort.closePort();

        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
}
