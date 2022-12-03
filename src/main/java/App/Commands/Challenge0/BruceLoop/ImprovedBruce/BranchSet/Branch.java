package App.Commands.Challenge0.BruceLoop.ImprovedBruce.BranchSet;

import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.io.File;
import java.io.Serializable;

/**
 * a branch set store the executed result of memory space, pointer, and store
 */
public class Branch implements Serializable {
    public static final File BRANCH_DIR = new File("src/main/java/App/Commands/Challenge0/BruceLoop/ImprovedBruce/BranchSet");

    private MemorySpace memorySpace;
    private Pointer pointer;
    private Store store;

    public Branch(MemorySpace memorySpace, Pointer pointer, Store store) {
        this.memorySpace = new MemorySpace(memorySpace);
        this.pointer = new Pointer(pointer);
        this.store = new Store(store);
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
}
