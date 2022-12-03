package App;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * On startup, the array is initialized to all 0s
 */
public class MemorySpace implements Serializable {
    private int scope;
    private ArrayList<Integer> memory;

    public MemorySpace() {
        scope = 10;
        memory = new ArrayList<Integer>();
        initializeMemory();
    }

    public MemorySpace(int scope) {
        this.scope = scope;
        memory = new ArrayList<Integer>();
        initializeMemory();
    }

    public MemorySpace(ArrayList<Integer> memory) {
        this.memory = memory;
        scope = memory.size();
    }

    public MemorySpace(MemorySpace memorySpace) {
        this.memory = memorySpace.memory;
        this.scope = memorySpace.scope;
    }

    public MemorySpace(String memorySpace) {
        memory = new ArrayList<Integer>();
        for (int i = 0; i < memorySpace.length(); i++) {
            this.memory.add(memorySpace.charAt(i) - '0');
        }
        this.scope = memory.size();
    }

    private void initializeMemory() {
        for (int i = 0; i < scope; i++) {
            memory.add(0);
        }
    }

    /**
     * if index is negative number, it reads the memory reversely, like python
     */
    public int getBit(Pointer ptr) {
        int index = ptr.getIndex();
        if (index < 0) {
            index = scope + index;
        }
        return memory.get(index);
    }

    public int getBitForTestOnly(int index) {
        return memory.get(index);
    }

    /**
     * if index is negative number, it reads the memory reversely, like python
     */
    public int setBit(Pointer ptr, int bit) {
        int index = ptr.getIndex();
        if (index < 0) {
            index = scope + index;
        }
        memory.set(index, bit);
        return bit;
    }

    public int getScope() {
        return scope;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (int i = 0; i < scope; i++) {
            result.append(memory.get(i));
            if (i != scope - 1) {
                result.append(", ");
            }
        }
        result.append("}");
        return result.toString();
    }

    public void reset() {
        for (int i = 0; i < scope; i++) {
            memory.set(i, 0);
        }
    }

    public void reset(int scope) {
        this.scope = scope;
        memory = new ArrayList<Integer>();
        initializeMemory();
    }

    public void reset(ArrayList<Integer> memory) {
        this.memory = memory;
        scope = memory.size();
    }

    public void reset(MemorySpace memorySpace) {
        this.memory = memorySpace.memory;
        this.scope = memorySpace.scope;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MemorySpace)) {
            return false;
        }
        MemorySpace memorySpace = (MemorySpace) obj;
        if (memorySpace.scope != this.scope) {
            return false;
        }
        for (int i = 0; i < scope; i++) {
            if (memorySpace.memory.get(i).compareTo(this.memory.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return memory.hashCode();
    }
}
