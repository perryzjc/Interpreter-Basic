package App.Commands.Challenge2.Prep.NInterlval1BitAddition;

import App.Commands.Challenge2.Prep.BAddNInterval;

/**
 * modification to challenge 1's BruceFindSolution
 * want to get a possible series commands for 1-bit addition that has no side effect for challenge2&3
 * <p>
 * this version focus on 1bit addition that can work correctly for A at 0, and B at 2, produce result at 4-6
 * solution found for this method: 15 commands
 */
public class BAdd2Interval {

    /**
     * INV
     * LOAD
     * INC
     * INC
     * INV
     * CDEC
     * LOAD
     * INV
     * INC
     * CDEC
     * LOAD
     * INC
     * INC
     * CDEC
     * INV
     * <p>
     * 15 commands
     */
    public static void main(String[] args) {
        boolean found;
        int nInterval = 2;
        for (int i = 10; i < 100; i++) {
            try {
                BAddNInterval bruceLoop = new BAddNInterval(i, nInterval);
                found = bruceLoop.exhaustivelyFindSolution();
            } catch (Exception e) {
                System.out.println("target command number: " + i + " is impossible to find solution based on my formula");
                System.out.println("exception: " + e.getMessage());
                System.out.println("skip to next target command number\n");
                continue;
            }
            System.out.println("target command: " + i);
            if (found) break;
        }
    }
}
