package App;

import java.io.Serializable;

/**
 * Equivalent to the Store
 * On startup, STORE = 0b0.
 */
public class Store implements Serializable {
    private int value;

    public Store() {
        value = 0;
    }

    public Store(Store store) {
        value = store.value;
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

    public void reset(int value) {
        this.value = value;
    }

    public void reset(Store store) {
        value = store.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Store)) return false;
        Store store = (Store) obj;
        return value == store.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Store: " + value;
    }
}
