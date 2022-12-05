package App.Commands.Strategy.PatternGuess;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;

import java.util.ArrayList;

public class AdditionPattern {

    public static ArrayList<Command> getFixedLast7Cmd(CmdHelper cmdHelper) {
        ArrayList<Command> last7Cmd = new ArrayList<>();
        last7Cmd.add(cmdHelper.getCmdINC());
        last7Cmd.add(cmdHelper.getCmdCDEC());
        last7Cmd.add(cmdHelper.getCmdLOAD());
        last7Cmd.add(cmdHelper.getCmdINC());
        last7Cmd.add(cmdHelper.getCmdINC());
        last7Cmd.add(cmdHelper.getCmdCDEC());
        last7Cmd.add(cmdHelper.getCmdINV());
        return last7Cmd;
    }

    public static ArrayList<Command> getFixedStartnCmd(CmdHelper cmdHelper, int bitInterval) {
        ArrayList<Command> start7Cmd = new ArrayList<>();
        start7Cmd.add(cmdHelper.getCmdINV());
        start7Cmd.add(cmdHelper.getCmdLOAD());
        for (int i = 0; i < bitInterval; i++) {
            start7Cmd.add(cmdHelper.getCmdINC());
        }
        start7Cmd.add(cmdHelper.getCmdINV());
        return start7Cmd;
    }
}
