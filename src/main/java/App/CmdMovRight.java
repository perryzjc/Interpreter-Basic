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
public class CmdMovRight extends DefinedCmd {
    private int steps;

    public CmdMovRight(int steps, Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        if (steps < 0) {
            throw new IllegalArgumentException("steps must be positive");
        }
        this.steps = steps;
        loadCommands();
    }

    @Override
    protected void loadCommands() {
        for (int i = 0; i < steps; i++) {
            cmdList.add(new CmdINC(pointer, memorySpace, store));
        }
    }
}
