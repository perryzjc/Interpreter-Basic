package App.Commands.Challenge0.BruceLoop.ImprovedBruce.MemorySet;

import App.MemorySpace;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;

public class MemorySet implements Serializable {
    public static final File MAP_DIR = new File("src/main/java/App/Commands/Challenge0/BruceLoop/ImprovedBruce/MemorySet");
    /**
     * key: a list of commands in string (separated by line)
     * e.g.
     * INC
     * INC
     * value: the memory space
     */
    private HashSet<MemorySpace> memSet;

    public MemorySet() {
        memSet = new HashSet<>();
    }

    public MemorySet(File file) {
        MemorySet memorySet = (MemorySet) Utils.readObject(file, MemorySet.class);
        memSet = memorySet.memSet;
    }

    public void add(MemorySpace mem) {
        memSet.add(new MemorySpace(mem));
    }

    public HashSet<MemorySpace> getMemSet() {
        return memSet;
    }

    public void serialize(String filename) {
        File file = new File(MAP_DIR, filename);
        Utils.writeObject(file, this);
    }

    public static MemorySet deserialize(String filename) {
        File file = new File(MAP_DIR, filename);
        return (MemorySet) Utils.readObject(file, MemorySet.class);
    }
}
