package App.Commands.Strategy;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;
import App.Commands.Strategy.PatternGuess.AdditionPattern;

import java.util.ArrayList;

public class GuessFor4bitsAddition extends ExtraStrategyFor1bitAddition {
    /**
     * based on the result from 1 bit, 2 bit, 3 bit addition
     * I guess there is a pattern for 4 bit addition
     * the start should be
     * INV
     * LOAD
     * INC
     * INC
     * INC
     * INC
     * INV
     *
     * one INC more than the 3 bit addition
     */
    private ArrayList<Command> first7CmdAsStart;
    private ArrayList<ArrayList<Command>> first7CmdAsStartList;
    private static final int NUM_START_CMD = 7;

    public GuessFor4bitsAddition(int max_cmd_used, CmdHelper cmdHelper) {
        super(max_cmd_used, cmdHelper);
        if (max_cmd_used < NUM_END_CMD + NUM_START_CMD + 1) {
            throw new IllegalArgumentException("max_cmd_used should be at least 15 for this strategy");
        }
        this.initUsableCmd();
    }

    @Override
    public ArrayList<Command> getInitStarterCmd() {
        return first7CmdAsStartList.get(0);
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
            return first7CmdAsStartList.get(curr_cmd_used);
        } else {
            return super.getMeaningfulCmd(curr_cmd_used);
        }
    }

    protected void initUsableCmd() {
        super.initUsableCmd();
        first7CmdAsStart = AdditionPattern.getFixedStart7Cmd(_cmdHelper, 4);
        first7CmdAsStartList = new ArrayList<>();

        for (Command cmd :first7CmdAsStart) {
            ArrayList<Command> onlyOneCmd = new ArrayList<>();
            onlyOneCmd.add(cmd);
            first7CmdAsStartList.add(onlyOneCmd);
            onlyOneCmd = null;
        }
    }
}
