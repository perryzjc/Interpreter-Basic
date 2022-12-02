import App.Commands.Basic.CmdCDEC;
import App.Commands.Basic.CmdINC;
import App.Commands.Basic.CmdINV;
import App.Commands.Basic.CmdLOAD;
import App.MemorySpace;
import App.Pointer;
import App.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test 4 basic commands: INC, INV, LOAD, CEDC
 */
public class TestBasicCmds {
    private MemorySpace memorySpace;
    private Pointer pointer;
    private Store store;

    @BeforeEach
    public void setUp() {
        memorySpace  = new MemorySpace(5);
        pointer = new Pointer();
        store = new Store();
    }

    @Test
    public void testINC() {
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
        new CmdINC(pointer, memorySpace, store).execute();
        assertEquals(1, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
    }

    @Test
    public void testINV() {
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
        new CmdINV(pointer, memorySpace, store).execute();
        assertEquals(0, pointer.getIndex());
        assertEquals(1, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
    }

    @Test
    public void testLOAD() {
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
        new CmdINV(pointer, memorySpace, store).execute();
        new CmdLOAD(pointer, memorySpace, store).execute();
        assertEquals(0, pointer.getIndex());
        assertEquals(1, memorySpace.getBit(pointer));
        assertEquals(1, store.getValue());
    }

    @Test
    public void testCDEC_StoreNot1() {
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
        new CmdLOAD(pointer, memorySpace, store).execute();
        new CmdINC(pointer, memorySpace, store).execute();
        assertEquals(1, pointer.getIndex());
        assertEquals(0, store.getValue());
        new CmdCDEC(pointer, memorySpace, store).execute();
        // pointer should not decrease, since store is not 1
        assertEquals(1, pointer.getIndex());
    }

    @Test
    public void testCDEC_StoreIs1() {
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
        new CmdINV(pointer, memorySpace, store).execute();
        new CmdLOAD(pointer, memorySpace, store).execute();
        new CmdINC(pointer, memorySpace, store).execute();
        assertEquals(1, pointer.getIndex());
        assertEquals(1, store.getValue());
        new CmdCDEC(pointer, memorySpace, store).execute();
        // pointer should decrease, since store is 1
        assertEquals(0, pointer.getIndex());
    }
}
