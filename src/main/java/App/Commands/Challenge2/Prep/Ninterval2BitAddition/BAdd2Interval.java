package App.Commands.Challenge2.Prep.Ninterval2BitAddition;


import App.Commands.Challenge2.Prep.BAddNInterval;

/**
 * Now, A&B can have two bits as input
 * e.g.
 * A: 00 01 10 11
 * B: 00 01 10 11
 * total 2^2 * 2^2 = 2^4 = 16 possible inputs
 */
public class BAdd2Interval {
    public static void main(String[] args) {
        boolean found;
        int nInterval = 16;
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
