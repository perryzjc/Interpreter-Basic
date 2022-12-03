package App.Commands.Basic;

import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * INV: invert the bit in the array at address ADDR ("flip the bit the head points at")
 */
public class CmdINV extends Command {
    public CmdINV(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
    }

    @Override
    public void execute() {
        boolean currBit = memorySpace.getBit(pointer);
        memorySpace.setBit(pointer, !currBit);
    }

    @Override
    public String commandName() {
        return "INV";
    }
}
