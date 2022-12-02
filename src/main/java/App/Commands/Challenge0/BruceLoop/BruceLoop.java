package App.Commands.Challenge0.BruceLoop;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;
import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

public class BruceLoop {
    private final ArrayList<Command> usableCommands = new ArrayList<>();
    private final ArrayList<Integer> currCombination = new ArrayList<>();
    private final ArrayList<Command> result = new ArrayList<>();
    private final int NUM_OPTIONS_CMD = 4;

    private int _max_commands_used;
    private Pointer pointer;
    private MemorySpace memorySpace;
    private Store store;
    private CmdHelper cmdHelper;

    public static void main(String[] args) {
        //finished: 3， 4， 5， 6， 7， 8， 9， 10， 11， 12， 13， 14， 15， 16
        for (int i = 3; i < 28; i++) {
            BruceLoop bruceLoop = new BruceLoop(i);
            bruceLoop.startForLoop();
            System.out.println("finished: " + i);
        }
        //BruceLoop bruceLoop = new BruceLoop(4);
    }

    enum INIT_STATE {
        T00, T01, T10, T11
    }

    public BruceLoop(int max_commands_used) {
        _max_commands_used = max_commands_used;
        pointer = new Pointer();
        memorySpace = new MemorySpace();
        store = new Store();
        cmdHelper = new CmdHelper(pointer, memorySpace, store);
        loadUsableCmd();
        initCurrCombination();
        initResult();
    }

    private void loadUsableCmd() {
        usableCommands.add(cmdHelper.getCmdINC());
        usableCommands.add(cmdHelper.getCmdINV());
        usableCommands.add(cmdHelper.getCmdLOAD());
        usableCommands.add(cmdHelper.getCmdCDEC());
    }

    private void initCurrCombination() {
        if (currCombination.size() == 0) {
            for (int i = 0; i < _max_commands_used; i++) {
                currCombination.add(0);
            }
        } else {
            for (int i = 0; i < _max_commands_used; i++) {
                currCombination.set(i, 0);
            }
        }
    }

    private void nextCombination() {
        int i = 0;
        while (i < NUM_OPTIONS_CMD) {
            if (currCombination.get(i) < NUM_OPTIONS_CMD - 1) {
                currCombination.set(i, currCombination.get(i) + 1);
                break;
            } else {
                currCombination.set(i, 0);
                i++;
            }
        }
    }

    private void initResult() {
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

    private void loadCommandsBasedOnCombinationArray() {
        for (int i = 0; i < _max_commands_used; i++) {
            Command cmd = usableCommands.get(currCombination.get(i));
            result.set(i, cmd);
        }
    }

    public void startForLoop() {
        long loopTimes = (long) Math.pow(NUM_OPTIONS_CMD, _max_commands_used);
        for (long i = 0; i < loopTimes; i++) {
           // System.out.println("Loop " + i);
            initResult();
            if (i != 0) {
                nextCombination();
            }
            loadCommandsBasedOnCombinationArray();
            if (!test000() || !test011() || !test101() || !test110()) {
                continue;
            } else {
                for (int j = 0; j < _max_commands_used; j++) {
                    System.out.println(result.get(j).commandName());
                }
                System.out.println("Found a solution!");
                return;
            }
        }
        System.out.println("Finished Not find a solution! looptimes: " + loopTimes);
    }

    private void resetState(INIT_STATE state) {
        /**
         * can perform 10 inc at max, thus only need 10 bits at max
         */
        switch (state) {
            case T00:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(0, 0));
                break;
            case T01:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(0, 1));
                break;
            case T10:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(1, 0));
                break;
            case T11:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(1, 1));
                break;
        }
        pointer.reset();
        store.reset();
    }

    private ArrayList<Integer> getSampleInitMemorySpaceForFirstTwoPos(int int1, int int2) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(int1);
        result.add(int2);
        for (int i = 2; i < _max_commands_used; i++) {
            result.add(0);
        }
        return result;
    }

    private boolean test000() {
        resetState(INIT_STATE.T00);
        new CurrDefinedCmd(pointer, memorySpace, store).execute();
        return memorySpace.getBitForTestOnly(2) == 0;
    }

    private boolean test011() {
        resetState(INIT_STATE.T01);
        new CurrDefinedCmd(pointer, memorySpace, store).execute();
        return memorySpace.getBitForTestOnly(2) == 1;
    }

    private boolean test101() {
        resetState(INIT_STATE.T10);
        new CurrDefinedCmd(pointer, memorySpace, store).execute();
        return memorySpace.getBitForTestOnly(2) == 1;
    }

    private boolean test110() {
        resetState(INIT_STATE.T11);
        new CurrDefinedCmd(pointer, memorySpace, store).execute();
        return memorySpace.getBitForTestOnly(2) == 0;
    }

    private class CurrDefinedCmd extends DefinedCmd {
        public CurrDefinedCmd(Pointer pointer, MemorySpace memorySpace, Store store) {
            super(pointer, memorySpace, store);
            loadCommands();
        }

        @Override
        protected void loadCommands() {
            for (int i = 0; i < _max_commands_used; i++) {
                Command cmd = result.get(i);
                cmdList.add(cmd);
            }
        }
    }
}
