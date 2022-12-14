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
        ArrayList<Command> startNCmd = new ArrayList<>();
        startNCmd.add(cmdHelper.getCmdINV());
        startNCmd.add(cmdHelper.getCmdLOAD());
        for (int i = 0; i < bitInterval; i++) {
            startNCmd.add(cmdHelper.getCmdINC());
        }
        startNCmd.add(cmdHelper.getCmdINV());
        startNCmd.add(cmdHelper.getCmdCDEC());
        startNCmd.add(cmdHelper.getCmdLOAD());
        startNCmd.add(cmdHelper.getCmdINV());
        return startNCmd;
    }

    public static ArrayList<Command> getFixedMiddleCmd(CmdHelper cmdHelper, int bitInterval) {
        ArrayList<Command> middleCmd = new ArrayList<>();
        if (bitInterval< 3) {
            return middleCmd;
        }
        middleCmd.add(cmdHelper.getCmdINC());
        middleCmd.add(cmdHelper.getCmdCDEC());
        middleCmd.add(cmdHelper.getCmdLOAD());
        for (int i = 0; i < bitInterval - 3; i++) {
            middleCmd.add(cmdHelper.getCmdINC());
        }
        return middleCmd;
    }
}
