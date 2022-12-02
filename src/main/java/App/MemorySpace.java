package App;

import java.util.ArrayList;

/**
 * On startup, the array is initialized to all 0s
 */
public class MemorySpace {
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
}
