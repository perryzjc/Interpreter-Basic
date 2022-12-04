package App.Commands.Challenge0.DefindCmd;

import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * CDEC
 * LOAD
 * CDEC
 * INC
 * LOAD
 * CDEC
 * INC
 * INC
 * LOAD
 * CDEC
 * INV
 * INC
 * INV
 */
public class CmdRecurSol30 extends DefinedCmd {

    public CmdRecurSol30(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        loadCommands();
    }

    @Override
    protected void loadCommands() {
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINV());
    }
}
