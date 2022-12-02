package App;

/**
 * On startup, ADDR = 0x0000
 * Since the tape is circular, when moving left at ADDR = 0x0000, it will move to the end of the tape
 * like how python index works
 */
public class Pointer {
    private int index;

    public Pointer() {
        index = 0;
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
}
