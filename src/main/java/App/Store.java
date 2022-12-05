package App;

import java.io.Serializable;

/**
 * Equivalent to the Store
 * On startup, STORE = 0b0.
 */
public class Store implements Serializable {
    private boolean value;

    public Store() {
        value = false;
    }

    public Store(Store store) {
        value = store.value;
    }

    public Store(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void reset() {
        value = false;
    }

    public void reset(boolean value) {
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
        return value ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Store: " + (value ? "1" : "0");
    }
}
