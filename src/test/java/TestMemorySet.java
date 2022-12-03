import App.Commands.Challenge0.BruceLoop.ImprovedBruce.MemorySet.MemorySet;
import App.MemorySpace;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

public class TestMemorySet {
    @Test
    public void testDeserialize() {
        MemorySet memorySet = MemorySet.deserialize("8.set");
        HashSet<MemorySpace> memSet = memorySet.getMemSet();
        assertTrue(memSet.contains(new MemorySpace("01100000")));
        assertTrue(memSet.contains(new MemorySpace("00010000")));
        assertTrue(memSet.contains(new MemorySpace("00100000")));
        assertTrue(memSet.contains(new MemorySpace("11000001")));
        assertTrue(memSet.contains(new MemorySpace("11000000")));
        assertTrue(memSet.contains(new MemorySpace("10000001")));
        assertTrue(memSet.contains(new MemorySpace("10100000")));
        assertTrue(memSet.contains(new MemorySpace("01010000")));
        assertTrue(memSet.contains(new MemorySpace("10000000")));
        assertTrue(memSet.contains(new MemorySpace("00000000")));
        assertTrue(memSet.contains(new MemorySpace("01000000")));
    }
}
