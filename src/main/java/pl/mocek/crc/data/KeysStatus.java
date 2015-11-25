package pl.mocek.crc.data;

public class KeysStatus {
    private boolean[] keys = new boolean[5];

    public boolean[] getStatus() {
        return keys;
    }

    public void setForward() {
        keys[0] = true;
    }

    public void releaseForward() {
        keys[0] = false;
    }

    public void setSlowForward() {
        keys[1] = true;
    }

    public void releaseSlowForward() {
        keys[1] = false;
    }

    public void setBack() {
        keys[2] = true;
    }

    public void releaseBack() {
        keys[2] = false;
    }

    public void setLeft() {
        keys[3] = true;
    }

    public void releaseLeft() {
        keys[3] = false;
    }

    public void setRight() {
        keys[4] = true;
    }

    public void releaseRight() {
        keys[4] = false;
    }
}