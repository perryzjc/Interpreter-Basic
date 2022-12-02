package App.Commands;

import App.MemorySpace;
import App.Pointer;
import App.Store;

/**
 * Challenge 0: XOR
 * Input: two bits  and  at addresses 0 and 1
 *
 * Output: the XOR of the bits, A XOR B, at address 2
 */
public class CmdXORSebastian extends DefinedCmd {

    public CmdXORSebastian(Pointer pointer, MemorySpace memorySpace, Store store) {
        super(pointer, memorySpace, store);
        loadCommands();
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
    @Override
    protected void loadCommands() {
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
}
