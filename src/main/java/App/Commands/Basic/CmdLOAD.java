package App.Commands.Basic;

import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * LOAD: load the bit in the array located at ADDR into STORE ("store the bit the head points to")
 */
public class CmdLOAD extends Command {
    public CmdLOAD(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
    }

    @Override
    public void execute() {
        store.setValue(memorySpace.getBit(pointer));
    }

    @Override
    public String commandName() {
        return "LOAD";
    }
}
