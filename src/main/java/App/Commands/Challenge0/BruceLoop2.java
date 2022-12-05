package App.Commands.Challenge0;

import App.ChallengeSetup;
import App.Commands.Basic.Command;
import App.Branch;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

/**
 * previous idea can not work
 * now optimize loop1 to an efficient way that take advantage of previous branch
 * using deep first search, shallow copy, and lazy evaluation
 *
 * best solution found by this class is only 12 commands
 */
public class BruceLoop2 extends ChallengeSetup {
    protected int starter_num_cmd;
    private int loopTimes;

    public static void main(String[] args) {
        BruceLoop2 bruceLoop = new BruceLoop2(12);
        bruceLoop.exhaustivelyFindSolution();
    }

    public BruceLoop2(int max_commands_used) {
        super(max_commands_used);
        starter_num_cmd = 1;
        loopTimes = 0;
    }

    public boolean exhaustivelyFindSolution() {
        if (starter_num_cmd < 1 || starter_num_cmd > _max_commands_used) {
            throw new RuntimeException("curr_commands_used should be in range [1, _max_commands_used]");
        }
        initResult();
        boolean found;
        Branch b00 = initBranch(false, false);
        Branch b01 = initBranch(false, true);
        Branch b10 = initBranch(true, false);
        Branch b11 = initBranch(true, true);
        ArrayList<Command> initUsableCmd = cmdAllocateStrategy.getInitStarterCmd();
        found = deepFirstSearch(starter_num_cmd, initUsableCmd, b00, b01, b10, b11);
        if (!found) {
            System.out.println("not found");
            System.out.println("loopTimes: " + loopTimes);
        }
        return found;
    }

    public boolean deepFirstSearch(int curr_commands_used, ArrayList<Command> usableCommands, Branch b00, Branch b01, Branch b10, Branch b11) {
        if (curr_commands_used > _max_commands_used) {
            cmdAllocateStrategy.traceBackLastStatus();
            return false;
        }
        boolean found;
        for (Command cmd : usableCommands) {
            loopTimes++;
            result.set(curr_commands_used - 1, cmd);
            //deep copy
            Branch resultB00 = new Branch(b00);
            Branch resultB01 = new Branch(b01);
            Branch resultB10 = new Branch(b10);
            Branch resultB11 = new Branch(b11);
            applyCmdTo4Branches(cmd, resultB00, resultB01, resultB10, resultB11);
            if (isAllTestPassed(resultB00, resultB01, resultB10, resultB11)) {
                handleFound(curr_commands_used);
                return true;
            }
            ArrayList<Command> newUsableCommands = cmdAllocateStrategy.nextUsableCommands(curr_commands_used, cmd);
            found = deepFirstSearch(curr_commands_used + 1, newUsableCommands, resultB00, resultB01, resultB10, resultB11);
            if (found) {
                return true;
            }
        }
        return false;
    }

    private void handleFound(int curr_commands_used) {
        for (Command cmd : result) {
            System.out.println(cmd.commandName());
        }
        System.out.println("\nFound a solution during recursion! Number of commands used: " + curr_commands_used);
        System.out.println("loopTimes: " + loopTimes);
    }

    private Branch initBranch(boolean firstBit, boolean secondBit) {
        MemorySpace memorySpace = memorySpaceForChallenge0();
        memorySpace.setBit(0, firstBit);
        memorySpace.setBit(1, secondBit);
        Pointer pointer = new Pointer(0);
        Store store = new Store();
        return new Branch(memorySpace, pointer, store);
    }

    /**
     * challenge require store the result at address 2
     * thus, it at least have 3 bits
     */
    private MemorySpace memorySpaceForChallenge0() {
        if (_max_commands_used < 3) {
            return new MemorySpace(3);
        } else {
            return new MemorySpace(_max_commands_used);
        }
    }

    /**
     * attention, tricky thing is that each command created by CmdHelper (static) is connected to reg branch
     * it's for efficiency
     *
     * thus, when using cmd, have to apply the branch to the reg branch
     * for efficiency, I can use shallow copy for branch
     */
    private void applyCmdTo4Branches(Command cmd, Branch b00, Branch b01, Branch b10, Branch b11) {
        applyBranchToRegBranch(b00);
        cmd.execute();
        readRegBranchToTargetBranch(b00);

        applyBranchToRegBranch(b01);
        cmd.execute();
        readRegBranchToTargetBranch(b01);

        applyBranchToRegBranch(b10);
        cmd.execute();
        readRegBranchToTargetBranch(b10);

        applyBranchToRegBranch(b11);
        cmd.execute();
        readRegBranchToTargetBranch(b11);

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

    private boolean isAllTestPassed(Branch b00, Branch b01, Branch b10, Branch b11) {
        return test000(b00) && test011(b01) && test101(b10) && test110(b11);
    }

    private boolean test000(Branch b00) {
        return !b00.getMemorySpace().getBitForTestOnly(2);
    }

    private boolean test011(Branch b01) {
        return b01.getMemorySpace().getBitForTestOnly(2);
    }

    private boolean test101(Branch b10) {
        return b10.getMemorySpace().getBitForTestOnly(2);
    }

    protected boolean test110(Branch b11) {
        return !b11.getMemorySpace().getBitForTestOnly(2);
    }
}
