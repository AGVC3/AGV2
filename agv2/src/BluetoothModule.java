package com.alg;

import TI.SerialConnection;


public class BluetoothModule implements Updatable {

    private SerialConnection conn;

    public  BluetoothModule() {
        this.conn = new SerialConnection(115200);
    }

    public void update() {
        if (this.conn.available() > 0) {
            int data = this.conn.readByte();
            char character = (char)data;
        }
    }
}
