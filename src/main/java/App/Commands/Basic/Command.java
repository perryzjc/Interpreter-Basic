package App.Commands.Basic;

import App.MemorySpace;
import App.Pointer;
import App.Store;

public abstract class Command  {
    protected Pointer pointer;
    protected MemorySpace memorySpace;
    protected Store store;

    public Command(Pointer pointer, MemorySpace memorySpace, Store register) {
        this.pointer = pointer;
        this.memorySpace = memorySpace;
        this.store = register;
    }

    abstract public void execute();
    abstract public String commandName();
}
