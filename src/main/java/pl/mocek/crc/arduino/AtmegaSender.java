package pl.mocek.crc.arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import pl.mocek.crc.data.KeysStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

public class AtmegaSender implements Runnable {

    //Serial communication settings
    public static final int DATA_RATE = 9600;
    public static final int TIMEOUT = 2000;
    private static final long DELAY = 10;
    //Serial communication constants
    private static final String START = "s\n";
    private static final String SET_1 = "1\n";
    private static final String SET_0 = "0\n";
    private KeysStatus keysStatus;
    //Communication
    private SerialPort serial;
    private OutputStream output;


    public AtmegaSender(KeysStatus keysStatus) {
        this.keysStatus = keysStatus;
    }

    private void initSerial() {
        CommPortIdentifier serialPortId = null;
        Enumeration enumComm = CommPortIdentifier.getPortIdentifiers();
        while (enumComm.hasMoreElements() && (serialPortId == null)) {
            serialPortId = (CommPortIdentifier) enumComm.nextElement();
        }

        try {
            serial = (SerialPort) serialPortId.open(getClass().getName(),
                    TIMEOUT);
            serial.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (PortInUseException | UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
    }

    private String[] getData() {
        boolean[] keys = keysStatus.getStatus();
        String[] data = new String[5];
        for (int i = 0; i < data.length; i++) {
            if (keys[i]) {
                data[i] = SET_1;
            } else {
                data[i] = SET_0;
            }
        }
        return data;
    }


    private void sendData(String[] data) {
        try {
            output.write(START.getBytes());
            for (String aData : data) {
                output.write(aData.getBytes());
                Thread.sleep(10);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void initOutputStream() {
        try {
            output = serial.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (serial != null) {
            serial.close();
        }
    }

    @Override
    public void run() {
        initSerial();
        initOutputStream();
        while (true) {
            String[] data = getData();
            sendData(data);
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}