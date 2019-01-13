import TI.SerialConnection;
import TI.Timer;

public class BluetoothModule implements Updatable {

    private Timer timer;
    private SerialConnection conn;
    private BluetoothModuleCallback callback;

    public BluetoothModule(BluetoothModuleCallback callback) {
        this.conn = new SerialConnection(115200);
        this.callback = callback;
        this.timer = new Timer(20);
    }

    public void update() {
        if (this.timer.timeout()) { //bluetooth data is received here
            if (this.conn.available() > 0) {
                int data = this.conn.readByte();
                System.out.println((char)data);
                this.callback.bluetoothDetect((char)data);
            }
        }
    }
}
