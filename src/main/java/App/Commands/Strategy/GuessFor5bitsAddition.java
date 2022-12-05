package App.Commands.Strategy;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;
import App.Commands.Strategy.PatternGuess.AdditionPattern;

import java.util.ArrayList;

public class GuessFor5bitsAddition extends ExtraStrategyFor1bitAddition {
    /**
     * based on the result from 1 bit, 2 bit, 3 bit, and 4 bit addition
     * I guess there is a pattern for 5 bit addition
     * the start should be
     * INV
     * LOAD
     * INC
     * INC
     * INC
     * INC
     * INC
     * INV
     *
     * 8 commands
     *
     * one INC more than the 3 bit addition
     */
    private ArrayList<Command> first8CmdAsStart;
    private ArrayList<ArrayList<Command>> first8CmdAsStartList;
    private static final int NUM_START_CMD = 8;

    public GuessFor5bitsAddition(int max_cmd_used, CmdHelper cmdHelper) {
        super(max_cmd_used, cmdHelper);
        if (max_cmd_used < NUM_END_CMD + NUM_START_CMD + 1) {
            throw new IllegalArgumentException("max_cmd_used should be at least 15 for this strategy");
        }
        this.initUsableCmd();
    }

    @Override
    public ArrayList<Command> getInitStarterCmd() {
        return first8CmdAsStartList.get(0);
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
            return first8CmdAsStartList.get(curr_cmd_used);
        } else {
            return super.getMeaningfulCmd(curr_cmd_used);
        }
    }

    protected void initUsableCmd() {
        super.initUsableCmd();
        first8CmdAsStart = AdditionPattern.getFixedStart7Cmd(_cmdHelper, 5);
        first8CmdAsStartList = new ArrayList<>();

        for (Command cmd : first8CmdAsStart) {
            ArrayList<Command> onlyOneCmd = new ArrayList<>();
            onlyOneCmd.add(cmd);
            first8CmdAsStartList.add(onlyOneCmd);
            onlyOneCmd = null;
        }
    }
}
