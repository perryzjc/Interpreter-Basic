package App;

import App.Branch.Branch;
import App.Commands.Basic.Command;
import App.Commands.Strategy.CmdAllocateStrategy;
import App.Commands.CmdHelper;

import java.util.ArrayList;

/**
 * common set up for each challenge
 *
 * attention: for efficiency, CmdHelper return static cmd that uses pointer, memorySpace, store in this object
 */
public class ChallengeSetup {
    protected final int NUM_OPTIONS_CMD = 4;

    protected Pointer pointer;
    protected MemorySpace memorySpace;
    protected Store store;
    protected CmdAllocateStrategy cmdAllocateStrategy;


    protected int _max_commands_used;
    protected ArrayList<Command> result;

    public ChallengeSetup(int max_commands_used) {
        pointer = new Pointer();
        memorySpace = new MemorySpace();
        store = new Store();
        cmdAllocateStrategy = new CmdAllocateStrategy(max_commands_used, new CmdHelper(pointer, memorySpace, store));
        result = new ArrayList<>();
        _max_commands_used = max_commands_used;
        initResult();
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

    /**
     * generate all possible initial branch state
     * e.g.
     * for challenge 0&1, only two bits are input
     * there 2^2 = 4 possible initial state
     *
     * for challenge 2,
     * input A at 0-15 and B at 16-31
     * based on discrete math
     * there are 2^16 * 2^16 = 2^32 possible initial state
     * 4294967296 is an extremely large number, my computer is unable to handle such many branches at the same time
     * If I want to try, I at least need replace each object,
     * since an empty object already cost 16bits
     * TODO: may become up with a equivalent way to simulates calculation to BitSet object, which use less memory
     *
     * TODO: for the time being, I think for challenge 2&3 I need to set up an init branch limit
     * just like the local test provided by the official, it only tests 100 different input files
     *
     * TODO: to verify the absolute correctness of the solution, I should come up with a way to test all possible initial state,
     * TODO: one of way is separate the init branches into different files, and handle each of them in different stage to avoid heap memory limit
     * TODO: this time, deep first search should not exist if find something
     * TODO: every command that work for the provided branches store to a command hashmap file
     * TODO: finally, the usable commands are the intersection of all command hashmap files
     *
     * TODO: new idea, is there a way to perform basic 1 bit calculation without side effect at least for challenge 2&3?
     * TODO: so that I can use those series of command repeated to get I want
     *
     */
    public ArrayList<Branch> generateInitBranchList() {
        return null;
    }
}
