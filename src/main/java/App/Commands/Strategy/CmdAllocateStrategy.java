package App.Commands.Strategy;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Command Allocate strategy, avoid meaning less command allocation
 * e.g.
 * it's meaningless to perform INV twice consecutively
 * it's true for LOAD as well
 *
 * ATTENTION: it's NOT true to NOT perform INC after CDEC or CDEC after INC
 * because CDEC can work only when store is 1
 *
 * Strategy3: INV has to be the last commands to avoid redundant commands
 */
public class CmdAllocateStrategy {
    protected int _max_cmd_used;
    /**
     * if not meaningful, strategy will return a commands list without this command
     */
    protected boolean INVMeaningful;
    protected boolean LOADMeaningful;
    protected Stack<CurrMeaningfulStatus> statusStack;

    protected CmdHelper _cmdHelper;
    protected ArrayList<Command> allBasicCmd;
    protected ArrayList<Command> allBasicCmdWithoutINV;
    protected ArrayList<Command> allBasicCmdWithoutLOAD;
    protected ArrayList<Command> allBasicCmdWithoutINVAndLOAD;
    protected ArrayList<Command> INVOnlyAsFinalCmd;
    protected static final int NUM_BASIC_CMD = 4;

    public CmdAllocateStrategy(int max_cmd_used, CmdHelper cmdHelper) {
        INVMeaningful = true;
        LOADMeaningful = true;
        _cmdHelper = cmdHelper;
        statusStack = new Stack<>();
        _max_cmd_used = max_cmd_used;
        initUsableCmd();
    }

    public ArrayList<Command> getInitStarterCmd() {
        return allBasicCmd;
    }

    /**
     * 0: CDEC
     * 1:
     * @return
     */
    public ArrayList<Command> nextUsableCommands(int curr_cmd_used, Command lastCmdUsed) {
        checkIfCmdMeaningful(lastCmdUsed);
        return getMeaningfulCmd(curr_cmd_used);
    }

    /**
     * trace back to the status that last command has not been used
     */
    public void traceBackLastStatus() {
        CurrMeaningfulStatus m = statusStack.pop();
        m.recoverStatus();
        m = null;
    }

    protected void checkIfCmdMeaningful(Command lastCmdUsed) {
        CurrMeaningfulStatus m = new CurrMeaningfulStatus();
        statusStack.push(m);
        m = null;

        if (lastCmdUsed == _cmdHelper.getCmdLOAD()) {
            LOADMeaningful = false;
            //1. if INV not meaningful, then keep it not meaningful
            //since after using load, pointer does not change
            //2. if INV already meaningful, then keep it meaningful
        } else if (lastCmdUsed == _cmdHelper.getCmdINV()) {
            INVMeaningful = false;
            LOADMeaningful = true;
        } else if (lastCmdUsed == _cmdHelper.getCmdINC()) {
            INVMeaningful = true;
            LOADMeaningful = true;
        } else {
            INVMeaningful = true;
            LOADMeaningful = true;
        }
    }

    /**
     * INV
     * LOAD
     * INC
     * INC
     * INV
     * CDEC
     * LOAD
     * INV
     * INC
     * CDEC
     * LOAD
     * INC
     * INC
     * CDEC
     * INV
     */
    protected ArrayList<Command> getMeaningfulCmd(int curr_cmd_used) {
        ArrayList<Command> ret = new ArrayList<>();
        ret.add(_cmdHelper.getCmdINV());
        ret.add(_cmdHelper.getCmdLOAD());
        ret.add(_cmdHelper.getCmdINC());
        ret.add(_cmdHelper.getCmdINC());
        ret.add(_cmdHelper.getCmdINV());
        ret.add(_cmdHelper.getCmdCDEC());
        ret.add(_cmdHelper.getCmdLOAD());
        ret.add(_cmdHelper.getCmdINV());
        ret.add(_cmdHelper.getCmdINC());
        ret.add(_cmdHelper.getCmdCDEC());
        ret.add(_cmdHelper.getCmdLOAD());
        ret.add(_cmdHelper.getCmdINC());
        ret.add(_cmdHelper.getCmdINC());
        ret.add(_cmdHelper.getCmdCDEC());
        ret.add(_cmdHelper.getCmdINV());
        ArrayList<Command> result = new ArrayList<>();
        result.add(ret.get(curr_cmd_used - 1));
        return result;
    }

    /**
     * since after INC or CDEC, LOAD and INV become meaningful,
     * the list logic can be simplified
     */
    protected void initUsableCmd() {
        allBasicCmd = new ArrayList<>();
        allBasicCmdWithoutINV = new ArrayList<>();
        allBasicCmdWithoutLOAD = new ArrayList<>();
        allBasicCmdWithoutINVAndLOAD = new ArrayList<>();
        INVOnlyAsFinalCmd = new ArrayList<>();

        allBasicCmd.add(_cmdHelper.getCmdLOAD());
        allBasicCmd.add(_cmdHelper.getCmdINV());
        allBasicCmd.add(_cmdHelper.getCmdINC());
        allBasicCmd.add(_cmdHelper.getCmdCDEC());

        allBasicCmdWithoutINV.add(_cmdHelper.getCmdLOAD());
        allBasicCmdWithoutINV.add(_cmdHelper.getCmdINC());
        allBasicCmdWithoutINV.add(_cmdHelper.getCmdCDEC());

        allBasicCmdWithoutLOAD.add(_cmdHelper.getCmdINV());
        allBasicCmdWithoutLOAD.add(_cmdHelper.getCmdINC());
        allBasicCmdWithoutLOAD.add(_cmdHelper.getCmdCDEC());

        allBasicCmdWithoutINVAndLOAD.add(_cmdHelper.getCmdINC());
        allBasicCmdWithoutINVAndLOAD.add(_cmdHelper.getCmdCDEC());

        INVOnlyAsFinalCmd.add(_cmdHelper.getCmdINV());
    }

    private class CurrMeaningfulStatus {
        private boolean INV;
        private boolean LOAD;

        public CurrMeaningfulStatus() {
            storeCurrStatus();
        }

        private void storeCurrStatus() {
            INV = INVMeaningful;
            LOAD = LOADMeaningful;
        }

        private void recoverStatus() {
            INVMeaningful = INV;
            LOADMeaningful = LOAD;
        }
    }
}
