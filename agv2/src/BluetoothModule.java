import TI.SerialConnection;

public class BluetoothModule implements Updatable {

    private SerialConnection conn;
    private BluetoothModuleCallback callback;

    public  BluetoothModule(BluetoothModuleCallback callback) {
        this.conn = new SerialConnection(115200);
        this.callback = callback;
    }

    public void update() {
        if (this.conn.available() > 0) {
            int data = this.conn.readByte();
            this.callback.bluetoothDetect((char)data);
        }
    }
}
