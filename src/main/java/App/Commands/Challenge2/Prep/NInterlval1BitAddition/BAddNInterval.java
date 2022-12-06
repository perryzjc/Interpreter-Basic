package App.Commands.Challenge2.Prep.NInterlval1BitAddition;

import App.Branch.Branch;
import App.Branch.BranchForAddition;
import App.ChallengeSetup;
import App.Commands.Basic.Command;
import App.Commands.CmdHelper;
import App.Commands.Strategy.GuessForNBitsAddition;
import App.Commands.Strategy.InitBranchGenerator;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

public class BAddNInterval extends ChallengeSetup {
    private final int _nInterval;
    protected int starter_num_cmd;
    private int loopTimes;

    public BAddNInterval(int max_commands_used, int nInterval) {
        super(max_commands_used);
        //TODO: test code for verify the correctness of the GuessForNBitsAddition class
        cmdAllocateStrategy = new GuessForNBitsAddition(nInterval, max_commands_used, new CmdHelper(pointer, memorySpace, store));
        _nInterval = nInterval;
        starter_num_cmd = 1;
        loopTimes = 0;
    }

    /**
     * INV
     * LOAD
     * INC
     * INC
     * INV
     * CDEC
     * LOAD
     * INV
     * INC
     * CDEC
     * LOAD
     * INC
     * INC
     * CDEC
     * INV
     * <p>
     * 15 commands
     */
    public static void main(String[] args) {
        boolean found;
        int nInterval = 16;
        for (int i = 14; i < 100; i++) {
            try {
                BAddNInterval bruceLoop = new BAddNInterval(i, nInterval);
                found = bruceLoop.exhaustivelyFindSolution();
            } catch (Exception e) {
                System.out.println("target command number: " + i + " is impossible to find solution based on my formula");
                System.out.println("exception: " + e.getMessage());
                System.out.println("skip to next target command number\n");
                continue;
            }
            System.out.println("target command: " + i);
            if (found) break;
        }
    }

    public boolean exhaustivelyFindSolution() {
        if (starter_num_cmd < 1 || starter_num_cmd > _max_commands_used) {
            throw new RuntimeException("curr_commands_used should be in range [1, _max_commands_used]");
        }
        initResult();
        boolean found;
        ArrayList<BranchForAddition> branches = InitBranchGenerator.getInitAdditionBranches(0, _nInterval, 1, _max_commands_used);
        ArrayList<Command> initUsableCmd = cmdAllocateStrategy.getInitStarterCmd();
        found = deepFirstSearch(starter_num_cmd, initUsableCmd, branches);
        if (!found) {
            System.out.println("not found");
            System.out.println("loopTimes: " + loopTimes);
        }
        return found;
    }

    public boolean deepFirstSearch(int curr_commands_used, ArrayList<Command> usableCommands, ArrayList<BranchForAddition> branches) {
        if (curr_commands_used > _max_commands_used) {
            cmdAllocateStrategy.traceBackLastStatus();
            branches = null;
            return false;
        }
        boolean found;
        for (Command cmd : usableCommands) {
            loopTimes++;
            result.set(curr_commands_used - 1, cmd);
            //deep copy
            ArrayList<BranchForAddition> newBranches = deepCopyBranches(branches);
            applyCmdToBranches(cmd, newBranches);
            if (isAllTestPassed(newBranches)) {
                handleFound(curr_commands_used);
                return true;
            }
            ArrayList<Command> newUsableCommands = cmdAllocateStrategy.nextUsableCommands(curr_commands_used, cmd);
            found = deepFirstSearch(curr_commands_used + 1, newUsableCommands, newBranches);
            if (found) {
                return true;
            }
            newBranches = null;
        }
        return false;
    }

    private ArrayList<BranchForAddition> deepCopyBranches(ArrayList<BranchForAddition> branches) {
        ArrayList<BranchForAddition> result = new ArrayList<>();
        for (BranchForAddition branch : branches) {
            result.add(new BranchForAddition(branch));
        }
        return result;
    }

    private void handleFound(int curr_commands_used) {
        for (Command cmd : result) {
            System.out.println(cmd.commandName());
        }
        System.out.println("\nFound a solution during recursion! Number of commands used: " + curr_commands_used);
        System.out.println("loopTimes: " + loopTimes);
    }

    private Branch initBranch(boolean firstBit, boolean secondBit) {
        MemorySpace memorySpace = memorySpaceForChallenge1();
        memorySpace.setBit(0, firstBit);
        memorySpace.setBit(2, secondBit);
        Pointer pointer = new Pointer(0);
        Store store = new Store();
        return new Branch(memorySpace, pointer, store);
    }

    /**
     * just in case, memory space for this version should have at least 300 bits
     */
    private MemorySpace memorySpaceForChallenge1() {
        if (_max_commands_used < 300) {
            return new MemorySpace(300);
        } else {
            return new MemorySpace(_max_commands_used);
        }
    }

    /**
     * attention, tricky thing is that each command created by CmdHelper (static) is connected to reg branch
     * it's for efficiency
     * <p>
     * thus, when using cmd, have to apply the branch to the reg branch
     * for efficiency, I can use shallow copy for branch
     */
    private void applyCmdToBranches(Command cmd, ArrayList<BranchForAddition> branches) {
        for (Branch b : branches) {
            applyBranchToRegBranch(b);
            cmd.execute();
            readRegBranchToTargetBranch(b);
        }
        safeClearReference();
    }

    private void applyBranchToRegBranch(Branch b) {
        //for efficiency, use shallow copy here
        memorySpace.shallowCopy(b.getMemorySpace());
        pointer.reset(b.getPointer());
        store.reset(b.getStore());
    }

    private void readRegBranchToTargetBranch(Branch b) {
        b.getMemorySpace().shallowCopy(memorySpace);
        b.getPointer().reset(pointer);
        b.getStore().reset(store);
    }

    private void safeClearReference() {
        memorySpace.clear();
        //this function mainly for testing purpose
    }

    /**
     * different from challenge 1, now the position got moved
     * first 0 at 0
     * second 0 at 16
     * result at 32-33
     */
    private boolean isAllTestPassed(ArrayList<BranchForAddition> branches) {
        for (BranchForAddition b : branches) {
            if (!b.isMetTargetResult()) {
                return false;
            }
        }
        return true;
    }
}
