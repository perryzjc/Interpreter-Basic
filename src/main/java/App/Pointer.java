package App;

/**
 * On startup, ADDR = 0x0000
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
}
