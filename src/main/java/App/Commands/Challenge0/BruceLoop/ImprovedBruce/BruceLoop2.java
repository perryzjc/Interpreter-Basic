package App.Commands.Challenge0.BruceLoop.ImprovedBruce;

import App.Commands.Challenge0.BruceLoop.BruceLoop;
import App.Commands.Challenge0.BruceLoop.ImprovedBruce.MemorySet.MemorySet;
import App.MemorySpace;

import java.io.File;

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
        BruceLoop2 bruceLoop = new BruceLoop2(7, 7, true);
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
        while(curr_commands_used <= _max_commands_used) {
            if (_isInitSet) {
                loadMemSet(curr_commands_used, true);
                nonDependentSetLoop();
                _isInitSet = false;
            } else {
                loadMemSet(curr_commands_used - 1, false);
                dependentSetLoop();
            }
            curr_commands_used++;
        }
    }

    public void nonDependentSetLoop() {
        long loopTimes = (long) Math.pow(NUM_OPTIONS_CMD, curr_commands_used);
        for (long i = 0; i < loopTimes; i++) {
            if (i != 0) {
                nextCombination();
            }
            currDefinedCmd.loadCommands();
            if (!test000() || !test011() || !test101() || !test110()) {
                continue;
            } else {
                loadToResult(currDefinedCmd);
                for (int j = 0; j < curr_commands_used; j++) {
                    System.out.println(result.get(j).commandName());
                }
                System.out.println("Found a solution! Number of commands used: " + curr_commands_used);
                return;
            }
        }
        String setName = curr_commands_used + ".set";
        memorySet.serialize(setName);
        System.out.println("Finished Not find a solution! loop times: " + loopTimes);
    }

    public void dependentSetLoop() {
        long loopTimes = 0;
        for (MemorySpace mem : memorySet.getMemSet()) {
            memorySpace.reset(mem);
            loopTimes++;
        }
    }

    @Override
    protected boolean test000() {
        boolean result = super.test000();
        memorySet.add(memorySpace);
        return result;
    }

    @Override
    protected boolean test011() {
        boolean result = super.test011();
        memorySet.add(memorySpace);
        return result;
    }

    @Override
    protected boolean test101() {
        boolean result = super.test101();
        memorySet.add(memorySpace);
        return result;
    }

    @Override
    protected boolean test110() {
        boolean result = super.test110();
        memorySet.add(memorySpace);
        return result;
    }
}
