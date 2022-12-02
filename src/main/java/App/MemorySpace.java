package App;


import java.util.ArrayList;

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

    public int getBit(Pointer ptr) {
        return memory.get(ptr.getIndex());
    }

    public int setBit(Pointer ptr, int bit) {
        return memory.set(ptr.getIndex(), bit);
    }

    public int getScope() {
        return scope;
    }
}
