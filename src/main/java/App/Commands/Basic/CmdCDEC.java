package App.Commands.Basic;

import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * CDEC: decrement the ADDR register if STORE = 0b1 ("move the head left if it's storing a 1")
 */
public class CmdCDEC extends Command {
    public CmdCDEC(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
    }

    @Override
    public void execute() {
        int index = pointer.getIndex();
        if (store.getValue() == 1) {
            pointer.setIndex(index - 1);
        }
    }
}
