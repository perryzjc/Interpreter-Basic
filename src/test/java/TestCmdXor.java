import App.Commands.Challenge0.CmdXORSebastian;
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
    private CmdXORSebastian cmdXOR;

    @BeforeEach
    public void init() {
        pointer = new Pointer();
        memorySpace = new MemorySpace();
        store = new Store();
        cmdXOR = new CmdXORSebastian(pointer, memorySpace, store);
    }

    @Test
    /**
     * 0 XOR 0 =  1
     */
    public void testCmdXOR_000() {
        testInitState();
        ArrayList<Integer> testSpace = getMaxArrayList(32);
        testSpace.set(0, 0);
        testSpace.set(1, 0);
        memorySpace.reset(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(0, memorySpace.getBit(pointer));
    }

    @Test
    /**
     * 0 XOR 1 =  0
     */
    public void testCmdXOR_011() {
        testInitState();
        ArrayList<Integer> testSpace = getMaxArrayList(32);
        testSpace.set(0, 0);
        testSpace.set(1, 1);
        memorySpace.reset(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(1, memorySpace.getBit(pointer));
    }

    @Test
    /**
     * 1 XOR 0 =  0
     */
    public void testCmdXOR_101() {
        testInitState();
        ArrayList<Integer> testSpace = getMaxArrayList(32);
        testSpace.set(0, 1);
        testSpace.set(1, 0);
        memorySpace.reset(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(1, memorySpace.getBit(pointer));
    }

    @Test
    /**
     * 1 XOR 1 =  1
     */
    public void testCmdXOR_110() {
        testInitState();
        ArrayList<Integer> testSpace = getMaxArrayList(32);
        testSpace.set(0, 1);
        testSpace.set(1, 1);
        memorySpace.reset(testSpace);
        cmdXOR.execute();
        pointer.setIndex(2);
        assertEquals(0, memorySpace.getBit(pointer));
    }

    @Test
    public void printCmdXOR() {
        System.out.println(cmdXOR.commandName());
    }

    /**
     * initial state for pointer, memory, and the store
     */
    private void testInitState() {
        assertEquals(0, pointer.getIndex());
        assertEquals(0, memorySpace.getBit(pointer));
        assertEquals(0, store.getValue());
    }

    private ArrayList<Integer> getMaxArrayList(int size) {
        ArrayList<Integer> maxArrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            maxArrayList.add(0);
        }
        return maxArrayList;
    }
}
