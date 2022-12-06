package App.Branch;

import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.io.Serializable;

/**
 * a branch set store the executed result of memory space, pointer, and store
 */
public class Branch implements Serializable {

    protected MemorySpace memorySpace;
    protected Pointer pointer;
    protected Store store;

    public Branch(MemorySpace memorySpace, Pointer pointer, Store store) {
        this.memorySpace = new MemorySpace(memorySpace);
        this.pointer = new Pointer(pointer);
        this.store = new Store(store);
    }

    public Branch(Branch branch) {
        this.memorySpace = new MemorySpace(branch.memorySpace);
        this.pointer = new Pointer(branch.pointer);
        this.store = new Store(branch.store);
    }

    public Branch(String branchString) {
        String[] branchStringArray = branchString.split(" ");
        this.memorySpace = new MemorySpace(branchStringArray[0]);
        this.pointer = new Pointer(Integer.parseInt(branchStringArray[1]));
        int storeBit = Integer.parseInt(branchStringArray[2]);
        this.store = new Store(storeBit == 1);
    }

    public MemorySpace getMemorySpace() {
        return memorySpace;
    }

    public Pointer getPointer() {
        return pointer;
    }

    public Store getStore() {
        return store;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Branch)) return false;
        Branch branch = (Branch) obj;
        return store.equals(branch.store) && pointer.equals(branch.pointer) && memorySpace.equals(branch.memorySpace);
    }

    @Override
    public int hashCode() {
        return store.hashCode() + pointer.hashCode() + memorySpace.hashCode();
    }

    @Override
    public String toString() {
        return memorySpace.toString() + " " + pointer.toString() + " " + store.toString();
    }
}
