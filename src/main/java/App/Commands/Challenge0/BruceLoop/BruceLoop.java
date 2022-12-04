package App.Commands.Challenge0.BruceLoop;

import App.Commands.Basic.Command;
import App.Commands.Challenge0.CmdXORSebastian;
import App.Commands.CmdHelper;
import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

public class BruceLoop {
    protected final static int SEBASTIAN_SOL_NUM_COMMANDS = 32;
    protected final ArrayList<Command> usableCommands = new ArrayList<>();
    protected final ArrayList<Integer> currCombination = new ArrayList<>();
    protected final int NUM_OPTIONS_CMD = 4;

    protected int _max_commands_used;
    protected Pointer pointer;
    protected MemorySpace memorySpace;
    protected Store store;
    protected CmdHelper cmdHelper;
    protected CurrDefinedCmd currDefinedCmd;
    protected ArrayList<Command> result;
    /* curr index to put the command in result */
    protected int currIndex;

    public static void main(String[] args) {
        boolean found = false;
        //finished: 3， 4， 5， 6， 7， 8， 9， 10， 11， 12， 13, 14, 15, 16, 17 (17179869184)
        for (int i = 3; i < 15; i++) {
            BruceLoop bruceLoop = new BruceLoop(24);
//            BruceLoop bruceLoop = new BruceLoop(SEBASTIAN_SOL_NUM_COMMANDS);
            found = bruceLoop.startForLoop();
            System.out.println("finished: " + i);
            if (found) break;
        }
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
        currDefinedCmd = new CurrDefinedCmd(pointer, memorySpace, store);
        result = new ArrayList<>();
        loadUsableCmd();
        initCurrCombination();
        initResult();
    }

    protected void loadUsableCmd() {
        usableCommands.add(cmdHelper.getCmdCDEC());
        usableCommands.add(cmdHelper.getCmdLOAD());
        usableCommands.add(cmdHelper.getCmdINV());
        usableCommands.add(cmdHelper.getCmdINC());
    }

    protected void initCurrCombination() {
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

    /**
     * increase from the first digit
     * e.g.
     * 0000 -> 1000 -> 2000 -> 3000 -> 4000 -> 0100 -> 1100
     */
    protected void nextCombination() {
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

    public boolean startForLoop() {
        long loopTimes = (long) Math.pow(NUM_OPTIONS_CMD, _max_commands_used);
        for (long i = 0; i < loopTimes; i++) {
           // System.out.println("Loop " + i);
            initResult();
            if (i != 0) {
                nextCombination();
            }
            currDefinedCmd.loadCommands();
            if (!test000() || !test011() || !test101() || !test110()) {
                continue;
            } else {
                loadToResult(currDefinedCmd);
                for (int j = 0; j < _max_commands_used; j++) {
                    System.out.println(result.get(j).commandName());
                }
                System.out.println("Found a solution! Number of commands used: " + result.size());
                return true;
            }
        }
        System.out.println("Finished Not find a solution! looptimes: " + loopTimes);
        return false;
    }

    protected void resetState(INIT_STATE state) {
        /**
         * can perform 10 inc at max, thus only need 10 bits at max
         */
        switch (state) {
            case T00:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(false, false));
                break;
            case T01:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(false, true));
                break;
            case T10:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(true, false));
                break;
            case T11:
                memorySpace.reset(getSampleInitMemorySpaceForFirstTwoPos(true, true));
                break;
        }
        pointer.reset();
        store.reset();
    }

    protected ArrayList<Boolean> getSampleInitMemorySpaceForFirstTwoPos(boolean t1, boolean t2) {
        ArrayList<Boolean> sample = new ArrayList<>();
        sample.add(t1);
        sample.add(t2);
        for (int i = 2; i < _max_commands_used; i++) {
            sample.add(false);
        }
        return sample;
    }

    protected boolean test000() {
        resetState(INIT_STATE.T00);
        currDefinedCmd.execute();
        return !memorySpace.getBitForTestOnly(2);
    }

    protected boolean test011() {
        resetState(INIT_STATE.T01);
        currDefinedCmd.execute();
        return memorySpace.getBitForTestOnly(2);
    }

    protected boolean test101() {
        resetState(INIT_STATE.T10);
        currDefinedCmd.execute();
        return memorySpace.getBitForTestOnly(2);
    }

    protected boolean test110() {
        resetState(INIT_STATE.T11);
        currDefinedCmd.execute();
        return !memorySpace.getBitForTestOnly(2);
    }

    /**
     * load every command in cmdList of definedCmd to result list
     * @param definedCmd
     */
    protected void loadToResult(DefinedCmd definedCmd) {
        currIndex = 0;
        loadToResultHelper(definedCmd);
    }

    protected void loadToResultHelper(DefinedCmd definedCmd) {
        ArrayList<Command> cmdList = definedCmd.getCmdList();
        for (int i = 0; i < cmdList.size(); i++) {
            Command command = cmdList.get(i);
            if (command instanceof DefinedCmd) {
                loadToResultHelper((DefinedCmd) command);
            } else {
                result.set(currIndex, command);
                currIndex++;
            }
        }
    }

    public void setNumCmdToUse(int numCmdToUse) {
        currDefinedCmd._numCmdToUse = numCmdToUse;
        ArrayList<Command> oldCmdList = new ArrayList<>(currDefinedCmd.getCmdList());
        ArrayList<Command> ptr = currDefinedCmd.getCmdList();
        ptr.clear();
        for (int i = 0; i < currDefinedCmd._numCmdToUse; i++) {
            ptr.add(oldCmdList.get(i));
        }
    }

    public class CurrDefinedCmd extends DefinedCmd {
        private int _numCmdToUse;

        public CurrDefinedCmd(Pointer pointer, MemorySpace memorySpace, Store store) {
            super(pointer, memorySpace, store);
            _numCmdToUse = _max_commands_used;
            init();
        }

        private void init() {
            for (int i = 0; i < _numCmdToUse; i++) {
                cmdList.add(null);
            }
        }

        @Override
        public void loadCommands() {
//            testCorrectCmdSebastianSolution();
            loadCommandsBasedOnCombinationArray();
        }

        private void testCorrectCmdSebastianSolution() {
            CmdXORSebastian cmdXORSebastian = new CmdXORSebastian(pointer, memorySpace, store);
            ArrayList<Command> cmdSource = cmdXORSebastian.getCmdList();
            for (int i = 0; i < _numCmdToUse; i++) {
                cmdList.set(i, cmdSource.get(i));
            }
        }

        private void loadCommandsBasedOnCombinationArray() {
            for (int i = 0; i < _numCmdToUse; i++) {
                Command cmd = usableCommands.get(currCombination.get(i));
                cmdList.set(i, cmd);
            }
        }
    }
}
