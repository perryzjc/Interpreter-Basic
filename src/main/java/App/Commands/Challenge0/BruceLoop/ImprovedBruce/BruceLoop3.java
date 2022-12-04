package App.Commands.Challenge0.BruceLoop.ImprovedBruce;

import App.Commands.Basic.Command;
import App.Commands.Challenge0.BruceLoop.ImprovedBruce.BranchSet.Branch;
import App.Commands.Challenge0.BruceLoop.ImprovedBruce.BranchSet.BranchSet;

import java.util.ArrayList;

/**
 * third version, using deep first search and lazy evaluation
 * to solve the issue that too many branches are saved in hashset at the same time
 *
 * TODO: NOT implemented yet, just ignore this class
 */
public class BruceLoop3 extends BruceLoop2{
    private ArrayList<Command> result;

    public static void main(String[] args) {
        BruceLoop2 bruceLoop = new BruceLoop3(30);
        bruceLoop.startForLoop();
    }

    public BruceLoop3(int max_commands_used) {
        super(1, max_commands_used, true);
    }

    @Override
    public void startForLoop() {
        initResult();
    }

    public boolean deepFirstSearch() {
        if (curr_commands_used != 1 || _max_commands_used <= 1) {
            throw new RuntimeException("deepFirstSearch() should only be called when curr_commands_used == 1");
        }
        boolean found = false;
        Branch initBranch = generateCurrBranch();
        for (Command cmd : usableCommands) {
            applyBranch(initBranch);
            result.set(curr_commands_used - 1, cmd);
            cmd.execute();
            found = isPassAllTest();
            if (found) break;
            Branch branch = generateCurrBranch();
            found = deepFirstSearchHelper(curr_commands_used + 1, branch);
        }
    }

    private boolean deepFirstSearchHelper(int curr_commands_used, Branch currBranch) {
        if (curr_commands_used > _max_commands_used) {
            return false;
        }
        boolean found = false;
        BranchSet childSet = new BranchSet();
        for (Command cmd : usableCommands) {
            applyBranch(currBranch);
            cmd.execute();
            if (isPassAllTest()) {
                System.out.println(curr_commands_used + ". " + cmd.commandName());
                System.out.println("Found a solution by dependentSetLoop! Number of commands used: " + curr_commands_used);
                return true;
            }
            Branch subBranch = new Branch(memorySpace, pointer, store);
            found = deepFirstSearchHelper(curr_commands_used + 1, subBranch);
            childSet.add(subBranch);
        }
        return found;
    }

    private boolean isPassAllTest() {
        boolean t1 = test000(true);
        boolean t2 = test011(true);
        boolean t3 = test101(true);
        boolean t4 = test110(true);
        return t1 && t2 && t3 && t4;
    }

    private Branch generateCurrBranch() {
        return new Branch(memorySpace, pointer, store);
    }

    private void applyBranch(Branch b) {
        memorySpace.reset(b.getMemorySpace());
        pointer.reset(b.getPointer());
        store.reset(b.getStore());
    }

    private BranchSet getInitBranchSet() {
        return null;
    }

}
