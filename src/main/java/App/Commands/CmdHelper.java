package App.Commands;

import App.Commands.Basic.CmdCDEC;
import App.Commands.Basic.CmdINC;
import App.Commands.Basic.CmdINV;
import App.Commands.Basic.CmdLOAD;
import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * each command require 3 arguments for constructor, it's redundant to write it
 * command helper can make those procedures as a function
 */
public class CmdHelper {
    private Pointer pointer;
    private MemorySpace memorySpace;
    private Store store;

    /**
     * singleton pattern
     * make the test more efficient
     */
    private static CmdINC cmdINC;
    private static CmdCDEC cmdCDEC;
    private static CmdINV cmdINV;
    private static CmdLOAD cmdLOAD;

    public CmdHelper(Pointer pointer, MemorySpace memorySpace, Store store) {
        this.pointer = pointer;
        this.memorySpace = memorySpace;
        this.store = store;
        cmdINC = new CmdINC(pointer, memorySpace, store);
        cmdCDEC = new CmdCDEC(pointer, memorySpace, store);
        cmdINV = new CmdINV(pointer, memorySpace, store);
        cmdLOAD = new CmdLOAD(pointer, memorySpace, store);
    }

    public CmdINC getCmdINC() {
        return cmdINC;
    }

    public CmdINV getCmdINV() {
        return cmdINV;
    }

    public CmdLOAD getCmdLOAD() {
        return cmdLOAD;
    }

    public CmdCDEC getCmdCDEC() {
        return cmdCDEC;
    }
}
