package App.Commands;

import App.Commands.Basic.Command;

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
    private int _max_cmd_used;
    /**
     * if not meaningful, strategy will return a commands list without this command
     */
    private boolean INVMeaningful;
    private boolean LOADMeaningful;
    private boolean INCMeaningful;
    private boolean CDECMeaningful;
    private Stack<CurrMeaningfulStatus> statusStack;

    private CmdHelper _cmdHelper;
    private ArrayList<Command> allBasicCmd;
    private ArrayList<Command> allBasicCmdWithoutINV;
    private ArrayList<Command> allBasicCmdWithoutLOAD;
    private ArrayList<Command> allBasicCmdWithoutINVAndLOAD;
    private ArrayList<Command> INVOnlyAsFinalCmd;
    private static final int NUM_BASIC_CMD = 4;

    public CmdAllocateStrategy(int max_cmd_used, CmdHelper cmdHelper) {
        INVMeaningful = true;
        LOADMeaningful = true;
        INCMeaningful = true;
        CDECMeaningful = true;
        _cmdHelper = cmdHelper;
        statusStack = new Stack<>();
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
    }

    private void checkIfCmdMeaningful(Command lastCmdUsed) {
        statusStack.push(new CurrMeaningfulStatus());

        if (lastCmdUsed == _cmdHelper.getCmdLOAD()) {
            LOADMeaningful = false;
            INCMeaningful = true;
            CDECMeaningful = true;
            //1. if INV not meaningful, then keep it not meaningful
            //since after using load, pointer does not change
            //2. if INV already meaningful, then keep it meaningful
        } else if (lastCmdUsed == _cmdHelper.getCmdINV()) {
            INVMeaningful = false;
            LOADMeaningful = true;
            INCMeaningful = true;
            CDECMeaningful = true;
        } else if (lastCmdUsed == _cmdHelper.getCmdINC()) {
            INVMeaningful = true;
            LOADMeaningful = true;
            INCMeaningful = true;
            CDECMeaningful = false; //CEDC immediately after INC is meaningless
        } else {
            INVMeaningful = true;
            LOADMeaningful = true;
            INCMeaningful = false; //INC immediately after CDEC is meaningless
            CDECMeaningful = true;
        }
    }

    private ArrayList<Command> getMeaningfulCmd(int curr_cmd_used) {
        if (curr_cmd_used == _max_cmd_used) {
            return INVOnlyAsFinalCmd;
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

    /**
     * since after INC or CDEC, LOAD and INV become meaningful,
     * the list logic can be simplified
     */
    private void initUsableCmd() {
        allBasicCmd = new ArrayList<>();
        allBasicCmdWithoutINV = new ArrayList<>();
        allBasicCmdWithoutLOAD = new ArrayList<>();
        allBasicCmdWithoutINVAndLOAD = new ArrayList<>();
        INVOnlyAsFinalCmd = new ArrayList<>();

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

        INVOnlyAsFinalCmd.add(_cmdHelper.getCmdINV());
    }

    private class CurrMeaningfulStatus {
        private boolean INV;
        private boolean LOAD;
        private boolean INC;
        private boolean CDEC;

        public CurrMeaningfulStatus() {
            storeCurrStatus();
        }

        private void storeCurrStatus() {
            INV = INVMeaningful;
            LOAD = LOADMeaningful;
            INC = INCMeaningful;
            CDEC = CDECMeaningful;
        }

        private void recoverStatus() {
            INVMeaningful = INV;
            LOADMeaningful = LOAD;
            INCMeaningful = INC;
            CDECMeaningful = CDEC;
        }
    }
}
