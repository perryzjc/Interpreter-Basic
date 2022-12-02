import App.CmdMovRight;
import App.MemorySpace;
import App.Pointer;
import App.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test mov right commands
 */
public class TestMovRight {
    private Pointer pointer;
    private MemorySpace memorySpace;
    private Store store;

    @BeforeEach
    public void init() {
        pointer = new Pointer();
        memorySpace = new MemorySpace();
        store = new Store();
    }

    @Test
    public void testMovRight() {
        testInitState();
        CmdMovRight cmdMovRight = new CmdMovRight(3, pointer, memorySpace, store);
        cmdMovRight.execute();
        assertEquals(3, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
    }

    @Test
    public void testMovNegative() {
        testInitState();
        assertThrows(IllegalArgumentException.class, () -> new CmdMovRight(-1, pointer, memorySpace, store));
    }

    private void testInitState() {
        pointer.setIndex(0);
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
    }
}
