package App.Commands.Strategy;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;
import App.Commands.Strategy.PatternGuess.AdditionPattern;

import java.util.ArrayList;

public class GuessForNBitsAddition extends ExtraStrategyFor1bitAddition {
    private ArrayList<Command> firstnCmdAsStart;
    private ArrayList<Command> midCmd;
    private ArrayList<ArrayList<Command>> firstnCmdAsStartList;
    private ArrayList<ArrayList<Command>> midCmdList;
    private int _bitInterval;
    private int NUM_START_CMD;

    public GuessForNBitsAddition(int bitInterval, int max_cmd_used, CmdHelper cmdHelper) {
        super(max_cmd_used, cmdHelper);
        NUM_START_CMD = bitInterval + 6;
        int requireCmd = NUM_START_CMD + NUM_END_CMD;
        if (max_cmd_used >= 19) {
            requireCmd += bitInterval;
        }
        if (max_cmd_used < requireCmd) {
            throw new IllegalArgumentException("max_cmd_used should be at least " + requireCmd + " for this strategy");
        }
        _bitInterval = bitInterval;
        this.initUsableCmd();
    }

    @Override
    public ArrayList<Command> getInitStarterCmd() {
        return firstnCmdAsStartList.get(0);
    }

    @Override
    public ArrayList<Command> nextUsableCommands(int curr_cmd_used, Command lastCmdUsed) {
        super.checkIfCmdMeaningful(lastCmdUsed);
        return this.getMeaningfulCmd(curr_cmd_used);
    }

    @Override
    /**
     * override the strategy for this new strategy
     */
    protected ArrayList<Command> getMeaningfulCmd(int curr_cmd_used) {
        if (curr_cmd_used < NUM_START_CMD) {
            return firstnCmdAsStartList.get(curr_cmd_used);
        } else if (curr_cmd_used < NUM_START_CMD + midCmd.size()) {
            return midCmdList.get(curr_cmd_used - NUM_START_CMD);
        } else {
            return super.getMeaningfulCmd(curr_cmd_used);
        }
    }

    protected void initUsableCmd() {
        super.initUsableCmd();
        firstnCmdAsStart = AdditionPattern.getFixedStartnCmd(_cmdHelper, _bitInterval);
        //midCmd is possible to be null
        midCmd = AdditionPattern.getFixedMiddleCmd(_cmdHelper, _bitInterval);
        firstnCmdAsStartList = new ArrayList<>();
        midCmdList = new ArrayList<>();

        for (Command cmd : firstnCmdAsStart) {
            ArrayList<Command> onlyOneCmd = new ArrayList<>();
            onlyOneCmd.add(cmd);
            firstnCmdAsStartList.add(onlyOneCmd);
            onlyOneCmd = null;
        }

        if (midCmd != null) {
            for (Command cmd : midCmd) {
                ArrayList<Command> onlyOneCmd = new ArrayList<>();
                onlyOneCmd.add(cmd);
                midCmdList.add(onlyOneCmd);
                onlyOneCmd = null;
            }
        }
    }
}
