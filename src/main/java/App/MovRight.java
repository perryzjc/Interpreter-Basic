package App;

import App.Commands.Basic.CmdINC;
import App.Commands.DefinedCmd;

/**
 * defined command based on basic commands
 * 1. only positive number allowed, for moving right
 * (because moving left has limitation, it can only move left when store has value 1)
 * (for moving left, check MovLeft commands)
 *
 * MovRight(2) =
 * INC
 * INC
 */
public class MovRight extends DefinedCmd {
    private int steps;

    public MovRight(int steps, Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        this.steps = steps;
    }

    @Override
    protected void loadCommands() {
        for (int i = 0; i < steps; i++) {
            cmdList.add(new CmdINC(pointer, memorySpace, store));
        }
    }
}
