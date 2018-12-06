import TI.SerialConnection;

public class BluetoothModule implements Updatable{

    private SerialConnection conn;

    public  BluetoothModule() {
        this.conn = new SerialConnection(115200);
    }


    @Override
    public void update() {
        if (conn.available() > 0) {
            int data = conn.readByte();
            char character = (char)data;
        }
    }
}
