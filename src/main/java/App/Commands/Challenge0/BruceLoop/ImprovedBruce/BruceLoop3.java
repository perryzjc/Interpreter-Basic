package App.Commands.Challenge0.BruceLoop.ImprovedBruce;

import App.Commands.Basic.Command;

import java.util.ArrayList;

/**
 * third version, using deep first search and lazy evaluation
 * to solve the issue that too many branches are saved in hashset at the same time
 *
 * TODO: NOT implemented yet, just ignore this class
 */
public class BruceLoop3 extends BruceLoop2{
    private ArrayList<Command> result;

    public static void main(String[] args) {
        BruceLoop2 bruceLoop = new BruceLoop3(30);
        bruceLoop.startForLoop();
    }

    /**
     * TODO: no implementation yet
     */
    public BruceLoop3(int max_commands_used) {
        super(1);
    }

    @Override
    public boolean startForLoop() {
        initResult();
        return false;
    }

}
