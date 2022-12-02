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

    public CmdHelper(Pointer pointer, MemorySpace memorySpace, Store store) {
        this.pointer = pointer;
        this.memorySpace = memorySpace;
        this.store = store;
    }

    public CmdINC getCmdINC() {
        return new CmdINC(pointer, memorySpace, store);
    }

    public CmdINV getCmdINV() {
        return new CmdINV(pointer, memorySpace, store);
    }

    public CmdLOAD getCmdLOAD() {
        return new CmdLOAD(pointer, memorySpace, store);
    }

    public CmdCDEC getCmdCDEC() {
        return new CmdCDEC(pointer, memorySpace, store);
    }
}
