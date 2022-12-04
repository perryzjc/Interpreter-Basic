import App.Commands.Challenge1.Solution.Challenge1Sol13Cmd;
import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestChallenge1 {
    private Pointer pointer;
    private MemorySpace memorySpace;
    private Store store;
    private DefinedCmd cmd1BitAdder;

    @BeforeEach
    public void init() {
        pointer = new Pointer();
        memorySpace = new MemorySpace();
        store = new Store();
        cmd1BitAdder = new Challenge1Sol13Cmd(pointer, memorySpace, store);
    }

    @Test
    public void test0000() {
        testInitState();
        ArrayList<Boolean> testSpace = getMaxArrayList(32);
        testSpace.set(0, false);
        testSpace.set(1, false);
        memorySpace.reset(testSpace);
        cmd1BitAdder.execute();
        assertFalse(memorySpace.getBitForTestOnly(2));
        assertFalse(memorySpace.getBitForTestOnly(3));
    }

    @Test
    public void test0110() {
        testInitState();
        ArrayList<Boolean> testSpace = getMaxArrayList(32);
        testSpace.set(0, false);
        testSpace.set(1, true);
        memorySpace.reset(testSpace);
        cmd1BitAdder.execute();
        assertTrue(memorySpace.getBitForTestOnly(2));
        assertFalse(memorySpace.getBitForTestOnly(3));
    }

    @Test
    public void test1010() {
        testInitState();
        ArrayList<Boolean> testSpace = getMaxArrayList(32);
        testSpace.set(0, true);
        testSpace.set(1, false);
        memorySpace.reset(testSpace);
        cmd1BitAdder.execute();
        assertTrue(memorySpace.getBitForTestOnly(2));
        assertFalse(memorySpace.getBitForTestOnly(3));
    }

    @Test
    /**
     * 1 XOR 1 =  1
     */
    public void test1101() {
        testInitState();
        ArrayList<Boolean> testSpace = getMaxArrayList(32);
        testSpace.set(0, true);
        testSpace.set(1, true);
        memorySpace.reset(testSpace);
        cmd1BitAdder.execute();
        assertFalse(memorySpace.getBitForTestOnly(2));
        assertTrue(memorySpace.getBitForTestOnly(3));
    }

    /**
     * initial state for pointer, memory, and the store
     */
    private void testInitState() {
        assertEquals(0, pointer.getIndex());
        assertEquals(false, memorySpace.getBit(pointer));
        assertEquals(false, store.getValue());
    }

    private ArrayList<Boolean> getMaxArrayList(int size) {
        ArrayList<Boolean> maxArrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            maxArrayList.add(false);
        }
        return maxArrayList;
    }
}
