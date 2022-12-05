package App.Commands.Strategy;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;

import java.util.ArrayList;
import java.util.Stack;

/**
 * additional strategy for 1-bit addition
 * e.g. A at 0, B at 1, result at 2-3
 * A at 0, B at 2, result at 4-6
 * A at 0, B at 3, result at 6-9
 * only 1 bit provided at A and B
 */
public class ExtraStrategyFor1bitAddition extends CmdAllocateStrategy{
    private ArrayList<Command> last7CmdAsEnd;
    private ArrayList<ArrayList<Command>> last7CmdAsEndList;
    private static final int NUM_END_CMD = 7;

    public ExtraStrategyFor1bitAddition(int max_cmd_used, CmdHelper cmdHelper) {
        super(max_cmd_used, cmdHelper);
        if (max_cmd_used < NUM_END_CMD) {
            throw new IllegalArgumentException("max_cmd_used should be at least 7 for this strategy");
        }
        this.initUsableCmd();
    }

    /**
     * 0: CDEC
     * 1:
     * @return
     */
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
        if (_max_cmd_used - curr_cmd_used + 1 <=  NUM_END_CMD) {
            return last7CmdAsEndList.get(NUM_END_CMD - (_max_cmd_used - curr_cmd_used));
        } else if (!INVMeaningful && !LOADMeaningful) {
            return allBasicCmdWithoutINVAndLOAD;
        } else if (!INVMeaningful) {
            return allBasicCmdWithoutINV;
        } else if (!LOADMeaningful) {
            return allBasicCmdWithoutLOAD;
        } else {
            return allBasicCmd;
        }
    }

    protected void initUsableCmd() {
        super.initUsableCmd();
        last7CmdAsEnd = new ArrayList<>();
        last7CmdAsEnd.add(_cmdHelper.getCmdINC());
        last7CmdAsEnd.add(_cmdHelper.getCmdCDEC());
        last7CmdAsEnd.add(_cmdHelper.getCmdLOAD());
        last7CmdAsEnd.add(_cmdHelper.getCmdINC());
        last7CmdAsEnd.add(_cmdHelper.getCmdINC());
        last7CmdAsEnd.add(_cmdHelper.getCmdCDEC());
        last7CmdAsEnd.add(_cmdHelper.getCmdINV());
        for (Command cmd : last7CmdAsEnd) {
            ArrayList<Command> onlyOneCmd = new ArrayList<>();
            onlyOneCmd.add(cmd);
            last7CmdAsEndList.add(onlyOneCmd);
            onlyOneCmd = null;
        }
    }
}
