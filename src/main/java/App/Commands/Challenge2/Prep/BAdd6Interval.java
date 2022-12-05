package App.Commands.Challenge2.Prep;

import App.*;
import App.Commands.Basic.Command;
import App.Commands.CmdHelper;
import App.Commands.Strategy.GuessForNBitsAddition;

import java.util.ArrayList;

/**
 * modification to challenge 1's BruceFindSolution
 * want to get a possible series commands for 1-bit addition that has no side effect for challenge2&3
 *
 * this version focus on 1bit addition that can work correctly for A at 0, and B at 6, produce result at 12-18
 * solution found for this method:
 */
public class BAdd6Interval extends ChallengeSetup {
    protected int starter_num_cmd;
    private int loopTimes;
    private static final int INPUT_A_INDEX = 0;
    private static final int INPUT_B_INDEX = 6;
    private static final int RESULT_INDEX_1 = 12;
    private static final int RESULT_INDEX_2 = 13;

    /**
     * INV
     * LOAD
     * INC
     * INC
     * INC
     * INC
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
     * INC
     * INC
     * CDEC
     * LOAD
     * INC
     * INC
     * CDEC
     * INV
     *
     * Found a solution during recursion! Number of commands used: 25
     */
    public static void main(String[] args) {
        boolean found;
        for (int i = 25; i < 28; i++) {
            try {
                BAdd6Interval bruceLoop = new BAdd6Interval(i);
                found = bruceLoop.exhaustivelyFindSolution();
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                break;
            }
            System.out.println("target command: " + i);
            if (found) break;
        }
    }


    public BAdd6Interval(int max_commands_used) {
        super(max_commands_used);
        //TODO: test code for verify the correctness of the GuessForNBitsAddition class
        cmdAllocateStrategy = new GuessForNBitsAddition(6, max_commands_used, new CmdHelper(pointer, memorySpace, store));
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
            b00 = null;
            b01 = null;
            b10 = null;
            b11 = null;
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
            resultB00 = null;
            resultB01 = null;
            resultB10 = null;
            resultB11 = null;
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
        memorySpace.setBit(INPUT_A_INDEX, firstBit);
        memorySpace.setBit(INPUT_B_INDEX, secondBit);
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

    /**
     * different from challenge 1, now the position got moved
     * first 0 at 0
     * second 0 at 16
     * result at 32-33
     */
    private boolean isAllTestPassed(Branch b00, Branch b01, Branch b10, Branch b11) {
        return test0000(b00) && test0110(b01) && test1010(b10) && test1101(b11);
    }

    private boolean test0000(Branch b00) {
        MemorySpace mem = b00.getMemorySpace();
        return !mem.getBitForTestOnly(RESULT_INDEX_1) && !mem.getBitForTestOnly(RESULT_INDEX_2);
    }

    private boolean test0110(Branch b01) {
        MemorySpace mem = b01.getMemorySpace();
        return mem.getBitForTestOnly(RESULT_INDEX_1) && !mem.getBitForTestOnly(RESULT_INDEX_2);
    }

    /**
     * result is logically equivalent to test0110
     */
    private boolean test1010(Branch b10) {
       return test0110(b10);
    }

    protected boolean test1101(Branch b11) {
        MemorySpace mem = b11.getMemorySpace();
        return !mem.getBitForTestOnly(RESULT_INDEX_1) && mem.getBitForTestOnly(RESULT_INDEX_2);
    }
}
