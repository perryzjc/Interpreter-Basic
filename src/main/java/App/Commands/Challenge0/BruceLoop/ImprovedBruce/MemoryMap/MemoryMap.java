package App.Commands.Challenge0.BruceLoop.ImprovedBruce.MemoryMap;

import App.MemorySpace;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MemoryMap implements Serializable {
    /**
     * key: a list of commands in string (separated by line)
     * e.g.
     * INC
     * INC
     * value: the memory space
     */
    private HashMap<String, MemorySpace> memMap;

    public MemoryMap() {
        memMap = new HashMap<>();
    }

    public MemoryMap(File file) {
        MemoryMap memoryMap = (MemoryMap) Utils.readObject(file, MemoryMap.class);
        memMap = memoryMap.getMemMap();
    }

    public void put(String key, MemorySpace value) {
        memMap.put(key, value);
    }

    public HashMap<String, MemorySpace> getMemMap() {
        return memMap;
    }

    public HashSet<MemorySpace> getMemSet() {
        return (HashSet<MemorySpace>) memMap.values();
    }

    public HashSet<HashMap.Entry<String, MemorySpace>> getEntrySet() {
        return (HashSet<HashMap.Entry<String, MemorySpace>>) memMap.entrySet();
    }
}
