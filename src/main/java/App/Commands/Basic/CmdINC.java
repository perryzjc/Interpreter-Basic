package App.Commands.Basic;

import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * INC: increment ADDR by 1 ("move the head right")
 */
public class CmdINC extends Command {
    public CmdINC(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
    }

    @Override
    public void execute() {
        int index = pointer.getIndex();
        pointer.setIndex(index + 1);
    }
}
