package App.Commands.Challenge2.Prep.NInterlval1BitAddition;

/**
 * modification to challenge 1's BruceFindSolution
 * want to get a possible series commands for 1-bit addition that has no side effect for challenge2&3
 * <p>
 * this version focus on 1bit addition that can work correctly for A at 0, and B at 3, produce result at 6-10
 * solution found for this method:
 */
public class BAdd3Interval {

    /**
     * INV
     * LOAD
     * INC
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
     * CDEC
     * LOAD
     * INC
     * INC
     * CDEC
     * INV
     * <p>
     * Found a solution during recursion! Number of commands used: 19
     * loopTimes: 19
     * target command: 19
     */
    public static void main(String[] args) {
        boolean found;
        int nInterval = 3;
        for (int i = 14; i < 100; i++) {
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
