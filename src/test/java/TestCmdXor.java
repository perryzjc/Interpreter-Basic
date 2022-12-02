import App.Commands.CmdHelper;
import App.Commands.CmdXOR;
import App.MemorySpace;
import App.Pointer;
import App.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test for challenge 0
 * https://github.com/radical-semiconductor/woodpecker
 *
 * Input: two bits  and  at addresses 0 and 1
 * Output: the XOR of the bits, A XOR B, at address 2
 */
public class TestCmdXor {
    private Pointer pointer;
    private MemorySpace memorySpace;
    private Store store;
    private CmdXOR cmdXOR;

    @BeforeEach
    public void init() {
        pointer = new Pointer();
        memorySpace = new MemorySpace(5);
        store = new Store();
        cmdXOR = new CmdXOR(pointer, memorySpace, store);
    }

    @Test
    /**
     * 0 XOR 0 =  1
     */
    public void testCmdXOR_001() {
        testInitState();
        ArrayList<Integer> testSpace = new ArrayList<>();
        testSpace.add(0);
        testSpace.add(0);
        testSpace.add(1);
        memorySpace = new MemorySpace(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(1, memorySpace.getBit(pointer));
    }

    @Test
    /**
     * 0 XOR 1 =  0
     */
    public void testCmdXOR_010() {
        testInitState();
        ArrayList<Integer> testSpace = new ArrayList<>();
        testSpace.add(0);
        testSpace.add(1);
        testSpace.add(0);
        memorySpace = new MemorySpace(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(0, memorySpace.getBit(pointer));
    }

    @Test
    /**
     * 1 XOR 0 =  0
     */
    public void testCmdXOR_100() {
        testInitState();
        ArrayList<Integer> testSpace = new ArrayList<>();
        testSpace.add(1);
        testSpace.add(0);
        testSpace.add(0);
        memorySpace = new MemorySpace(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(0, memorySpace.getBit(pointer));
    }

    @Test
    /**
     * 1 XOR 1 =  1
     */
    public void testCmdXOR_110() {
        testInitState();
        ArrayList<Integer> testSpace = new ArrayList<>();
        testSpace.add(1);
        testSpace.add(1);
        testSpace.add(1);
        memorySpace = new MemorySpace(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(1, memorySpace.getBit(pointer));
    }

    /**
     * initial state for pointer, memory, and the store
     */
    private void testInitState() {
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
    }
}
