package App.Commands.Challenge0;

import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * Challenge 0: XOR
 * Input: two bits A and B at addresses 0 and 1
 *
 * Output: the XOR of the bits, A XOR B, at address 2
 */
public class CmdXorJingchao extends DefinedCmd {
    public CmdXorJingchao(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        loadCommands();
    }

    @Override
    protected void loadCommands() {

    }
}
