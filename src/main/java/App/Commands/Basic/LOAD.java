package App.Commands.Basic;

import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * LOAD: load the bit in the array located at ADDR into STORE ("store the bit the head points to")
 */
public class LOAD extends Command {
    public LOAD(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
    }

    @Override
    public void execute() {
        store.setValue(memorySpace.getBit(pointer));
    }
}
