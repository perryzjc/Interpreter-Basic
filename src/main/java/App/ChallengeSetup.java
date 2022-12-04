package App;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;

import java.util.ArrayList;

/**
 * common set up for each challenge
 *
 * attention: for efficiency, CmdHelper return static cmd that uses pointer, memorySpace, store in this object
 */
public class ChallengeSetup {
    protected final int NUM_OPTIONS_CMD = 4;
    protected final ArrayList<Command> usableCommands = new ArrayList<>();

    protected Pointer pointer;
    protected MemorySpace memorySpace;
    protected Store store;
    protected CmdHelper cmdHelper;


    protected int _max_commands_used;
    protected ArrayList<Command> result;

    public ChallengeSetup(int max_commands_used) {
        pointer = new Pointer();
        memorySpace = new MemorySpace();
        store = new Store();
        cmdHelper = new CmdHelper(pointer, memorySpace, store);
        result = new ArrayList<>();
        _max_commands_used = max_commands_used;
        loadUsableCmd();
        initResult();
    }

    private void loadUsableCmd() {
        usableCommands.add(cmdHelper.getCmdCDEC());
        usableCommands.add(cmdHelper.getCmdLOAD());
        usableCommands.add(cmdHelper.getCmdINV());
        usableCommands.add(cmdHelper.getCmdINC());
    }

    protected void initResult() {
        if (result.size() == 0) {
            for (int i = 0; i < _max_commands_used; i++) {
                result.add(null);
            }
        } else {
            for (int i = 0; i < _max_commands_used; i++) {
                result.set(i, null);
            }
        }
    }
}
