package App.Commands.Challenge1;

import App.ChallengeSetup;
import App.Branch;
import App.Commands.Basic.Command;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

/**
 * Challenge 1: 1-bit addition
 * Input: two bits A and B at addresses 0 and 1
 *
 * Output: the 2-bit sum of the bits, A + B , at addresses 2-3.
 *
 * NOTE: all integers are represented LSB first in memory.
 * LSB means the rightmost (least-significant) bit is the first bit.
 * MSB means the leftmost (most-significant) is the first bit.
 */
public class BruceFindSolution extends ChallengeSetup {
    protected int starter_num_cmd;
    private int loopTimes;

    /**
     * LOAD
     * INC
     * INV
     * CDEC
     * INV
     * LOAD
     * CDEC
     * INC
     * LOAD
     * CDEC
     * INC
     * INC
     * INV
     * my program verify that at least 13 commands needed
     */
    public static void main(String[] args) {
        BruceFindSolution bruceLoop = new BruceFindSolution(13);
        bruceLoop.exhaustivelyFindSolution();
    }

    public BruceFindSolution(int max_commands_used) {
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
        if (curr_commands_used > _max_commands_used) return false;
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
            ArrayList<Command> newUsableCommands = cmdAllocateStrategy.nextUsableCommands(cmd);
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
        MemorySpace memorySpace = memorySpaceForChallenge1();
        memorySpace.setBit(0, firstBit);
        memorySpace.setBit(1, secondBit);
        Pointer pointer = new Pointer(0);
        Store store = new Store();
        return new Branch(memorySpace, pointer, store);
    }

    /**
     * challenge require store the result at address 2-3
     * thus, it at least have 4 bits
     */
    private MemorySpace memorySpaceForChallenge1() {
        if (_max_commands_used < 4) {
            return new MemorySpace(4);
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
        return test0000(b00) && test0110(b01) && test1010(b10) && test1101(b11);
    }

    private boolean test0000(Branch b00) {
        MemorySpace mem = b00.getMemorySpace();
        return !mem.getBitForTestOnly(2) && !mem.getBitForTestOnly(3);
    }

    private boolean test0110(Branch b01) {
        MemorySpace mem = b01.getMemorySpace();
        return mem.getBitForTestOnly(2) && !mem.getBitForTestOnly(3);
    }

    /**
     * result is logically equivalent to test0110
     */
    private boolean test1010(Branch b10) {
       return test0110(b10);
    }

    protected boolean test1101(Branch b11) {
        MemorySpace mem = b11.getMemorySpace();
        return !mem.getBitForTestOnly(2) && mem.getBitForTestOnly(3);
    }
}
