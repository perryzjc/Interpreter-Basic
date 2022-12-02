package App.Commands;

import App.Commands.Basic.Command;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

/**
 * Challenge 0: XOR
 * Input: two bits  and  at addresses 0 and 1
 *
 * Output: the XOR of the bits, A XOR B, at address 2
 */
public class CmdXOR extends Command {
    private CmdHelper cmdHelper;
    private ArrayList<Command> cmdList;

    public CmdXOR(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        cmdHelper = new CmdHelper(pointer, memorySpace, store);
        cmdList = new ArrayList<>();
        loadCommands();
    }

    @Override
    public void execute() {
        for (Command cmd : cmdList) {
            cmd.execute();
        }
    }

    /**
     * XOR command is a combination of 4 basic commands
     * load the 4 commands into the commands arraylist
     *
     * answer from sebastian:
     * 0. LOAD
     * 1. CDEC
     * 2. CDEC
     * 3. INC
     * 4. LOAD
     * 5. INC
     * 6. INC
     * 7. LOAD
     * 8. CDEC
     * 9. CDEC
     * 10. CDEC
     * 11. LOAD
     * 12. INC
     * 13. INV
     * 14. LOAD
     * 15. CDEC
     * 16. CDEC
     * 17. CDEC
     * 18. INV
     * 19. LOAD
     * 20. INV
     * 21. CDEC
     * 22. INC
     * 23. INV
     * 24. INC
     */
    private void loadCommands() {
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdLOAD());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdCDEC());
        cmdList.add(cmdHelper.getCmdINC());
        cmdList.add(cmdHelper.getCmdINV());
        cmdList.add(cmdHelper.getCmdINC());
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
}
