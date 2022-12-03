package App;

import java.io.Serializable;

/**
 * On startup, ADDR = 0x0000
 * Since the tape is circular, when moving left at ADDR = 0x0000, it will move to the end of the tape
 * like how python index works
 */
public class Pointer implements Serializable {
    private int index;

    public Pointer() {
        index = 0;
    }

    public Pointer(Pointer pointer) {
        index = pointer.index;
    }

    public Pointer(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void reset() {
        index = 0;
    }

    public void reset(int index) {
        this.index = index;
    }

    public void reset(Pointer pointer) {
        index = pointer.index;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Pointer)) return false;
        Pointer pointer = (Pointer) obj;
        return index == pointer.index;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public String toString() {
        return String.format("Pointer: %d", index);
    }
}
