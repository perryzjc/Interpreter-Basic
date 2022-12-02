package App;

/**
 * Equivalent to the Store
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
}
