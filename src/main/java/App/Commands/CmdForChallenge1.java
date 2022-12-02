package App.Commands;

import App.Commands.Basic.Command;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

/**
 * Challenge 1: 1-bit addition
 * Input: two bits A and B at addresses 0 and 1
 *
 * Output: the 2-bit sum of the bits, A + B, at addresses 2-3.
 */
public class CmdForChallenge1 extends Command {
    private CmdHelper cmdHelper;
    private ArrayList<Command> cmdList;

    public CmdForChallenge1(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        cmdHelper = new CmdHelper(pointer, memorySpace, store);
        cmdList = new ArrayList<>();
        loadCommands();
    }
    @Override
    public void execute() {

    }

    @Override
    public String commandName() {
        return null;
    }

    private void loadCommands() {

    }
}
