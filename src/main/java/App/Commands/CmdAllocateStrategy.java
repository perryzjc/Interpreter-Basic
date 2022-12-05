package App.Commands;

import App.Commands.Basic.Command;

import java.util.ArrayList;

/**
 * Command Allocate strategy, avoid meaning less command allocation
 * e.g.
 * it's meaningless to perform INV twice consecutively
 * it's true for LOAD as well
 */
public class CmdAllocateStrategy {
    /**
     * if not meaningful, strategy will return a commands list without this command
     */
    private boolean INVMeaningful;
    private boolean LOADMeaningful;
    private CmdHelper _cmdHelper;
    private ArrayList<Command> allBasicCmd;
    private ArrayList<Command> allBasicCmdWithoutINV;
    private ArrayList<Command> allBasicCmdWithoutLOAD;
    private ArrayList<Command> allBasicCmdWithoutINVAndLOAD;
    private static final int NUM_BASIC_CMD = 4;

    CmdAllocateStrategy(CmdHelper cmdHelper) {
        INVMeaningful = true;
        LOADMeaningful = true;
        _cmdHelper = cmdHelper;
        initUsableCmd();
    }

    /**
     * 0: CDEC
     * 1:
     * @return
     */
    public ArrayList<Command> nextUsableCommands(Command lastCmdUsed) {
        checkIfCmdMeaningful(lastCmdUsed);
        return getMeaningfulCmd();
    }

    private void checkIfCmdMeaningful(Command lastCmdUsed) {
        if (lastCmdUsed == _cmdHelper.getCmdLOAD()) {
            LOADMeaningful = false;
            //if INV not meaningful, then keep it not meaningful
            //since after using load, pointer does not change
        } else if (lastCmdUsed == _cmdHelper.getCmdINV()) {
            INVMeaningful = false;
            LOADMeaningful = true;
        } else {
            INVMeaningful = true;
            LOADMeaningful = true;
        }
    }

    private ArrayList<Command> getMeaningfulCmd() {
        if (INVMeaningful && LOADMeaningful) {
            return allBasicCmd;
        } else if (INVMeaningful) {
            return allBasicCmdWithoutLOAD;
        } else if (LOADMeaningful) {
            return allBasicCmdWithoutINV;
        } else {
            return allBasicCmdWithoutINVAndLOAD;
        }
    }

    private void initUsableCmd() {
        allBasicCmd = new ArrayList<>();
        allBasicCmdWithoutINV = new ArrayList<>();
        allBasicCmdWithoutLOAD = new ArrayList<>();
        allBasicCmdWithoutINVAndLOAD = new ArrayList<>();

        allBasicCmd.add(_cmdHelper.getCmdCDEC());
        allBasicCmd.add(_cmdHelper.getCmdLOAD());
        allBasicCmd.add(_cmdHelper.getCmdINV());
        allBasicCmd.add(_cmdHelper.getCmdINC());

        allBasicCmdWithoutINV.add(_cmdHelper.getCmdCDEC());
        allBasicCmdWithoutINV.add(_cmdHelper.getCmdLOAD());
        allBasicCmdWithoutINV.add(_cmdHelper.getCmdINC());

        allBasicCmdWithoutLOAD.add(_cmdHelper.getCmdCDEC());
        allBasicCmdWithoutLOAD.add(_cmdHelper.getCmdINV());
        allBasicCmdWithoutLOAD.add(_cmdHelper.getCmdINC());

        allBasicCmdWithoutINVAndLOAD.add(_cmdHelper.getCmdCDEC());
        allBasicCmdWithoutINVAndLOAD.add(_cmdHelper.getCmdINC());
    }
}
