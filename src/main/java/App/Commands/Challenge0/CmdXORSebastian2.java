package App.Commands.Challenge0;

import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * INV
 * LOAD
 * INV
 * CDEC
 * CDEC
 * CDEC
 * INC
 * INV
 * LOAD
 * INV
 * CDEC
 * CDEC
 * CDEC
 * INC
 * INC
 * INC
 * INC
 * INV
 * INC
 * INC
 * LOAD
 * INC
 * INC
 * INC
 * CDEC
 * CDEC
 * CDEC
 * INC
 * INV
 */
public class CmdXORSebastian2 extends DefinedCmd {

    public CmdXORSebastian2(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        loadCommands();
    }

    @Override
    protected void loadCommands() {
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINV());
    }
}
