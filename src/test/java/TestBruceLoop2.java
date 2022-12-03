import App.Commands.Challenge0.BruceLoop.ImprovedBruce.BranchSet.Branch;
import App.Commands.Challenge0.BruceLoop.ImprovedBruce.BranchSet.BranchSet;
import App.Commands.Challenge0.BruceLoop.ImprovedBruce.BruceLoop2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBruceLoop2 {
//    @Test
//    public void testBruceLoop2SebastianSol() {
//        BruceLoop2 bruceLoop2 = new BruceLoop2(3, 20, true);
//        bruceLoop2.startForLoop();
//        BranchSet branchSet = bruceLoop2.getBranchSet();
//        assertTrue(branchSet.contains(correctSebastian000()));
//        assertTrue(branchSet.contains(correctSebastian011()));
//        assertTrue(branchSet.contains(correctSebastian101()));
//        assertTrue(branchSet.contains(correctSebastian110()));
//    }

    @Test
    public void testBruceLoop2SmallData() {
        BruceLoop2 bruceLoop2 = new BruceLoop2(4, 4, true);
        bruceLoop2.startForLoop();
        BranchSet branchSet = bruceLoop2.getBranchSet();
        Branch expected = inc2Inv1Inc1();
        assertTrue(branchSet.contains(expected));
    }

    @Test
    public void testInv1Load1DCEC2() {
        BruceLoop2 bruceLoop2 = new BruceLoop2(4, 4, true);
        bruceLoop2.startForLoop();
        BranchSet branchSet = bruceLoop2.getBranchSet();
        Branch expected = inv1Load1DCEC2();
        assertTrue(branchSet.contains(expected));
    }

    private Branch inv1Load1DCEC2() {
        StringBuilder sb = new StringBuilder();
        String memory = "1000";
        String pointer = "-2";
        String store = "1";
        sb.append(memory).append(" ").append(pointer).append(" ").append(store);
        return new Branch(sb.toString());
    }

    @Test
    public void testInv1Load1DCEC4INV1INC3() {
        BruceLoop2 bruceLoop2 = new BruceLoop2(3, 9, true);
        bruceLoop2.startForLoop();
        BranchSet branchSet = bruceLoop2.getBranchSet();
        Branch expected = inv1Load1DCEC4INV1INC3();
        assertTrue(branchSet.contains(expected));
    }

    private Branch inv1Load1DCEC4INV1INC3() {
        StringBuilder sb = new StringBuilder();
        String memory = "100001000";
        String pointer = "-1";
        String store = "1";
        sb.append(memory).append(" ").append(pointer).append(" ").append(store);
        return new Branch(sb.toString());
    }

    private Branch inc2Inv1Inc1() {
        StringBuilder sb = new StringBuilder();
        //001 + 29's 0
        String memory = "0010";
        String pointer = "2";
        String store = "0";
        sb.append(memory).append(" ").append(pointer).append(" ").append(store);
        return new Branch(sb.toString());
    }

    private Branch correctSebastian000() {
        //{0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        StringBuilder branchString = new StringBuilder();
        String memStr = "01001000100000000000000000000000";
        String pointer = "8";
        String store = "1";
        branchString.append(memStr).append(" ").append(pointer).append(" ").append(store);
        return new Branch(branchString.toString());
    }

    private Branch correctSebastian011() {
        //{0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        StringBuilder branchString = new StringBuilder();
        String memStr = "01101000010000000000000000000000";
        String pointer = "9";
        String store = "0";
        branchString.append(memStr).append(" ").append(pointer).append(" ").append(store);
        return new Branch(branchString.toString());
    }

    private Branch correctSebastian101() {
        //{1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        StringBuilder branchString = new StringBuilder();
        String memStr = "10100010000000000000000000000001";
        String pointer = "6";
        String store = "1";
        branchString.append(memStr).append(" ").append(pointer).append(" ").append(store);
        return new Branch(branchString.toString());
    }

    private Branch correctSebastian110() {
        //{1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}
        StringBuilder branchString = new StringBuilder();
        String memStr = "11010000000000000000000000001001";
        String pointer = "3";
        String store = "1";
        branchString.append(memStr).append(" ").append(pointer).append(" ").append(store);
        return new Branch(branchString.toString());
    }

    @Test
    public void testSeba0031Before32() {
        sebstian0031CmdsBefore32();
        BruceLoop2 bruceLoop2 = new BruceLoop2(32, 32, false);
        bruceLoop2.startForLoop();
        BranchSet branchSet = bruceLoop2.getBranchSet();
        assertTrue(branchSet.contains(correctSebastian000()));
    }

    @Test
    public void testSeba1031Before32() {
        sebstian1031CmdsBefore32();
        BruceLoop2 bruceLoop2 = new BruceLoop2(32, 32, false);
        bruceLoop2.startForLoop();
        BranchSet branchSet = bruceLoop2.getBranchSet();
        assertTrue(branchSet.contains(correctSebastian101()));
    }

    private void sebstian0031CmdsBefore32() {
        BranchSet branchSet = new BranchSet();
        //{false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        StringBuilder branchString = new StringBuilder();
        String memStr = "01001000000000000000000000000000";
        String pointer = "8";
        String store = "1";
        branchString.append(memStr).append(" ").append(pointer).append(" ").append(store);
        Branch branch = new Branch(branchString.toString());
        branchSet.add(branch);
        branchSet.serialize(31);
    }

    private void sebstian1031CmdsBefore32() {
        BranchSet branchSet = new BranchSet();
        //{true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true}
        StringBuilder branchString = new StringBuilder();
        String memStr = "10100000000000000000000000000001";
        String pointer = "6";
        String store = "1";
        branchString.append(memStr).append(" ").append(pointer).append(" ").append(store);
        Branch branch = new Branch(branchString.toString());
        branchSet.add(branch);
        branchSet.serialize(31);
    }
}
