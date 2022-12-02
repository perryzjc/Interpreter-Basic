package App.Commands;

import App.Commands.Basic.Command;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

public class CmdXorJingchao extends DefinedCmd {
    public CmdXorJingchao(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        loadCommands();
    }

    @Override
    protected void loadCommands() {

    }
}
