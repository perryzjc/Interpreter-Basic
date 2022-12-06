package App.Commands.Strategy;

import App.Branch.Branch;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

/**
 * generate the initial branch needed for testing
 * 1 bit addition for A at 0, B at N, result at 2n to 2n+1, require 2^1 * 2^1 = 4 branches for complete testing
 * 2 bit addition for A at 0, B at N, result at 2n to 2n+2, require 2^2 * 2^2 = 16 branches for complete testing
 */
public class InitBranchGenerator {
    private static int _max_cmd_used;
    private static int _startA;
    private static int _startB;
    private static int _bitInterval;
    private static int _nBitsAddition;
    private static int combinationIndex;
    private static int numCombination;
    private static ArrayList<MemorySpace> memCombinations;

    public static ArrayList<Branch> getInitBranches(int startA, int bitInterval, int nBitsAddition, int max_cmd_used) {
        _max_cmd_used = max_cmd_used;
        _startA = startA;
        _startB = startA + bitInterval;
        _bitInterval = bitInterval;
        _nBitsAddition = nBitsAddition;
        initMmeCombinations();
        ArrayList<Branch> branches = new ArrayList<>();
        for (int i = 0; i < numCombination; i++) {
            branches.add(nextBranch());
        }
        return branches;
    }

    /**
     * GIVE ME JAVA methods to implement this condition
     * and provide test code (from the following e.g.) to verify the correctness of this method
     * don't forgot to output the result at the end of each recursion
     * don't calculate the result
     * only show the possible initial branches
     * don't put methods inside a method, java does not allow it
     *
     * e.g.
     * 1 bit addition, interval 1 bit, for A at 0, B at 1, result at 2 to 3, require 2^1 * 2^1 = 4 branches for complete testing
     * possible branches (4 branches): 00, 01, 10, 11
     * 1 bit addition, interval 2 bit, for A at 0, B at 2, result at 4 to 5, require 2^1 * 2^1 = 4 branches for complete testing
     * possible branches (4 branches): 000, 001, 100, 101
     * 2 bit addition, interval 2 bit, for A at 0, B at 2, result at 4 to 6, require 2^2 * 2^2 = 16 branches for complete testing
     * possible branches (16 branches): 0000, 0001, 0010, 0011, 0100, 0101, 0110, 0111, 1000, 1001, 1010, 1011, 1100, 1101, 1110, 1111
     * 2 bit addition, interval 3 bit, for A at 0, B at 3, result at 6 to 8, require 2^2 * 2^2 = 16 branches for complete testing
     * possible branches (16 branches): 00000, 00001, 00010, 00011, 00100, 00101, 00110, 00111, 01000, 01001, 01010, 01011, 01100, 01101, 01110, 01111
     * 3 bit addition, interval 3 bit, for A at 0, B at 3, result at 6 to 9, require 2^3 * 2^3 = 64 branches for complete testing
     * possible branches (64 branches): 000000, 000001, 000010, 000011, 000100, 000101, 000110, 000111, 001000, 001001, 001010, 001011, 001100, 001101, 001110, 001111, 010000, 010001, 010010, 010011, 010100, 010101, 010110, 010111, 011000, 011001, 011010, 011011, 011100, 011101, 011110, 011111, 100000, 100001, 100010, 100011, 100100, 100101, 100110, 100111, 101000, 101001, 101010, 101011, 101100, 101101, 101110, 101111, 110000, 110001, 110010, 110011, 110100, 110101, 110110, 110111, 111000, 111001, 111010, 111011, 111100, 111101, 111110, 111111
     *
     * it's a binary tree structure, each node has 2 children, height of the tree is 2^n, for n bits addition, consider height start from 0
     */
    private static void initMmeCombinations() {
        combinationIndex = 0;
        numCombination = (int) Math.pow(2, _nBitsAddition * 2);
        memCombinations = new ArrayList<>();

        ArrayList<ArrayList<Boolean>> memHelper = getMemCombinationsHelper();
        for (ArrayList<Boolean> mem : memHelper) {
            MemorySpace memorySpace = memorySpaceForAdditionChallenge();
            int count = 0;
            for (int i = 0; i < _nBitsAddition; i++) {
                memorySpace.setBit(i, mem.get(count));
                count++;
            }
            for(int i = _nBitsAddition; i < 2 * _nBitsAddition; i++) {
                memorySpace.setBit(i + _bitInterval - 1, mem.get(count));
                count++;
            }
            memCombinations.add(memorySpace);
        }
    }

    /**
     * {0, 0}, {0, 1}, {1, 0}, {1, 1}
     * {0,0,0,0}, {0,0,0,1}, {0,0,1,0}, {0,0,1,1}, {0,1,0,0}, {0,1,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,0,0}, {1,0,0,1}, {1,0,1,0}, {1,0,1,1}, {1,1,0,0}, {1,1,0,1}, {1,1,1,0}, {1,1,1,1}
     *
     * this method only depends on numCombination (_nBitsAddition)
     */
    private static ArrayList<ArrayList<Boolean>> getMemCombinationsHelper() {
        ArrayList<ArrayList<Boolean>> memCombinations = new ArrayList<>();
        for (int i = 0; i < numCombination; i++) {
            ArrayList<Boolean> memCombination = new ArrayList<>();
            for (int j = 0; j < _nBitsAddition * 2; j++) {
                memCombination.add((i & (1 << j)) != 0);
            }
            memCombinations.add(memCombination);
        }
        return memCombinations;
    }

    private static Branch nextBranch() {
        MemorySpace memorySpace = memCombinations.get(combinationIndex);
        combinationIndex++;
        Pointer pointer = new Pointer(0);
        Store store = new Store();
        return new Branch(memorySpace, pointer, store);
    }

    /**
     * just in case, memory space for this version should have at least 300 bits
     */
    private static MemorySpace memorySpaceForAdditionChallenge() {
        if (_max_cmd_used < 300) {
            return new MemorySpace(300);
        } else {
            return new MemorySpace(_max_cmd_used);
        }
    }
}
