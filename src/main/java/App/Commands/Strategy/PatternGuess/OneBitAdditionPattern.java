package App.Commands.Strategy.PatternGuess;

import App.Commands.DefinedCmd.DefinedCmd;
import App.MemorySpace;
import App.Pointer;
import App.Store;

public class OneBitAdditionPattern {
    private int _bitInterval;

    public OneBitAdditionPattern(int bitInterval, Pointer pointer, MemorySpace memorySpace, Store store) {
        _bitInterval = bitInterval;
    }

    private DefinedCmd generatePattern(Pointer pointer, MemorySpace memorySpace, Store store) {
        DefinedCmd pattern = new GuessSolution(pointer, memorySpace, store);
        return pattern;
    }

    private class GuessSolution extends DefinedCmd {

        public GuessSolution(Pointer pointer, MemorySpace memorySpace, Store store) {
            super(pointer, memorySpace, store);
        }

        @Override
        protected void loadCommands() {
            cmdList.add(cmdHelper.getCmdINV());
            cmdList.add(cmdHelper.getCmdLOAD());
            for (int i = 0; i < _bitInterval; i++) {
                cmdList.add(cmdHelper.getCmdINC());
            }
            cmdList.add(cmdHelper.getCmdCDEC());
            cmdList.add(cmdHelper.getCmdLOAD());
            cmdList.add(cmdHelper.getCmdINV());
            cmdList.add(cmdHelper.getCmdINC());
            cmdList.add(cmdHelper.getCmdCDEC());
            cmdList.add(cmdHelper.getCmdLOAD());
            for (int i = 0; i < _bitInterval - 2; i++) {
                cmdList.add(cmdHelper.getCmdINC());
            }
            cmdList.add(cmdHelper.getCmdCDEC());
            cmdList.add(cmdHelper.getCmdLOAD());
        }
    }
}
