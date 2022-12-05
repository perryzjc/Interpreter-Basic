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
}
