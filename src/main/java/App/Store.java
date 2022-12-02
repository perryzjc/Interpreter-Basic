package App;

/**
 * Equivalent to the Store
 * On startup, STORE = 0b0.
 */
public class Store {
    private int value;

    public Store() {
        value = 0;
    }

    public Store(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void reset() {
        value = 0;
    }
}
