package App.Commands.DefinedCmd;

import App.Commands.Basic.Command;
import App.Commands.CmdHelper;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

/**
 * self-defined command
 * all command should be based on basic commands
 */
public abstract class DefinedCmd extends Command {
    protected CmdHelper cmdHelper;
    protected ArrayList<Command> cmdList;

    public DefinedCmd(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        cmdHelper = new CmdHelper(pointer, memorySpace, store);
        cmdList = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (cmdList.size() == 0) {
            throw new RuntimeException("defined command is not loaded with basic commands yet");
        }
        for (Command cmd : cmdList) {
            cmd.execute();
        }
    }

    @Override
    public String commandName() {
        StringBuilder result = new StringBuilder();
        for (Command cmd : cmdList) {
            result.append(cmd.commandName());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * load the basic commands into the commands arraylist
     */
    protected abstract void loadCommands();
}
