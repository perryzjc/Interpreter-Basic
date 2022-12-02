package App.Commands.Challenge1;

import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * Challenge 1: 1-bit addition
 * Input: two bits A and B at addresses 0 and 1
 *
 * Output: the 2-bit sum of the bits, A + B, at addresses 2-3.
 */
public class CmdForChallenge1 extends DefinedCmd {

    public CmdForChallenge1(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
    }

    @Override
    protected void loadCommands() {

    }
}