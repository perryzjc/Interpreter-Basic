package App.Commands.Challenge0.BruceLoop.ImprovedBruce;

import App.Commands.Basic.Command;
import App.Commands.Challenge0.BruceLoop.BruceLoop;
import App.Commands.Challenge0.BruceLoop.ImprovedBruce.MemorySet.MemorySet;
import App.MemorySpace;



/**
 * an optimization of BruceLoop.java
 * have an additional map to store different result of memory space after a defined command is executed
 * e.g. 4 commands defined command, the map will store 2^4 (at max) different memory space
 * many memory space are the same, so the memory map will keep the memory space only once
 *
 * 17 commands will operate the deserialized result of 16.map file
 * 18 commands will operate the deserialized result of 17.map file
 * thus improve the performance
 *
 * ATTENTION: when using this loop, for safety, pre-define the max-allow spaces (which equals to the max-allow commands)
 */
public class BruceLoop2 extends BruceLoop {

    private MemorySet memorySet;
    private int curr_commands_used;
    boolean _isInitSet;

    public static void main(String[] args) {
        //finished: 3， 4， 5， 6， 7， 8， 9， 10， 11， 12， 13, 14, 15, 16, 17 (17179869184)
        BruceLoop2 bruceLoop = new BruceLoop2(3, 35, true);
        bruceLoop.startForLoop();
    }

    public BruceLoop2(int start_num_commands, int max_commands_used, boolean isInitSet) {
        super(max_commands_used);
        curr_commands_used = start_num_commands;
        _isInitSet = isInitSet;
    }

    private void loadMemSet(int commands_used, boolean isInitSet) {
        if (isInitSet) {
            memorySet = new MemorySet();
        } else {
            String filename = commands_used + ".set";
            memorySet = MemorySet.deserialize(filename);
        }
    }

    @Override
    public void startForLoop() {
        boolean found = false;
        while(curr_commands_used <= _max_commands_used && !found) {
            if (_isInitSet) {
                loadMemSet(curr_commands_used, true);
                found = nonDependentSetLoop();
                _isInitSet = false;
            } else {
                loadMemSet(curr_commands_used - 1, false);
                found = dependentSetLoop();
            }
            curr_commands_used++;
        }
    }

    public boolean nonDependentSetLoop() {
        long loopTimes = (long) Math.pow(NUM_OPTIONS_CMD, curr_commands_used);
        for (long i = 0; i < loopTimes; i++) {
            if (i != 0) {
                nextCombination();
            }
            currDefinedCmd.loadCommands();
            boolean true1 = test000(false);
            boolean true2 = test011(false);
            boolean true3 = test101(false);
            boolean true4 = test110(false);
            if (true1 && true2 && true3 && true4) {
                loadToResult(currDefinedCmd);
                for (int j = 0; j < curr_commands_used; j++) {
                    System.out.println(result.get(j).commandName());
                }
                System.out.println("Found a solution by nonDependentSetLoop! Number of commands used: " + curr_commands_used);
                return true;
            }
        }
        String setName = curr_commands_used + ".set";
        memorySet.serialize(setName);
        System.out.println("Finished Not find a solution! loop times: " + loopTimes);
        return false;
    }

    public boolean dependentSetLoop() {
        long loopTimes = 0;
        MemorySet oldMemorySet = new MemorySet(memorySet);
        memorySet = new MemorySet();
        for (MemorySpace mem : oldMemorySet.getMemSet()) {
            for (Command cmd : usableCommands) {
                loopTimes++;
                memorySpace.reset(mem);
                pointer.reset();
                store.reset();
                cmd.execute();
                memorySet.add(memorySpace);
                boolean t1 = test000(true);
                boolean t2 = test011(true);
                boolean t3 = test101(true);
                boolean t4 = test110(true);
                if (t1 && t2 && t3 && t4) {
                    System.out.println(curr_commands_used + ". " + cmd.commandName());
                    System.out.println("Found a solution by dependentSetLoop! Number of commands used: " + curr_commands_used);
                    return true;
                }
            }
        }
        String setName = curr_commands_used + ".set";
        memorySet.serialize(setName);
        System.out.println("Finished Not find a solution! loop times: " + loopTimes);
        return false;
    }

    protected boolean test000(boolean dependentSet) {
        boolean result;
        if (dependentSet) {
            result = memorySpace.getBitForTestOnly(2) == 0;
        } else {
            result = super.test000();
            memorySet.add(memorySpace);
        }
        return result;
    }

    protected boolean test011(boolean dependentSet) {
        boolean result;
        if (dependentSet) {
            result = memorySpace.getBitForTestOnly(1) == 1;
        } else {
            result = super.test011();
            memorySet.add(memorySpace);
        }
        return result;
    }

    protected boolean test101(boolean dependentSet) {
        boolean result;
        if (dependentSet) {
            result = memorySpace.getBitForTestOnly(0) == 1;
        } else {
            result = super.test101();
            memorySet.add(memorySpace);
        }
        return result;
    }

    protected boolean test110(boolean dependentSet) {
        boolean result;
        if (dependentSet) {
            result = memorySpace.getBitForTestOnly(0) == 0;
        } else {
            result = super.test110();
            memorySet.add(memorySpace);
        }
        return result;
    }
}
