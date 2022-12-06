package App.Branch;

import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.ArrayList;

public class BranchForAddition extends Branch {
    /**
     * only store the bits of the result range
     * thus pay attention to the offset
     */
    private ArrayList<Boolean> targetAdditionResult;
    private int _startA;
    private int _bitInterval;
    private int _nBitsAddition;

    public BranchForAddition(MemorySpace memorySpace, Pointer pointer, Store store, int startA, int bitInterval, int nBitsAddition) {
        super(memorySpace, pointer, store);
        _startA = startA;
        _bitInterval = bitInterval;
        _nBitsAddition = nBitsAddition;
        setUpTargetResult();
    }

    /**
     * e.g.
     * given a sample input memory space
     * {0, 0, 1, 0}, A at index 0, B at index 2
     * inputA: 1 1, input B: 1 0
     * this method calculate the result of A + B and save it to targetAdditionResult
     * it's binary data, so the expected result is {0, 0, 1}
     * since it's LSB
     *
     * the max length of result = _nBitsAddition + 1
     */
    private void setUpTargetResult() {
        targetAdditionResult = new ArrayList<>();
        int indexA = _startA;
        int indexB = _startA + _bitInterval;
        int carry = 0;
        for (int i = 0; i < _nBitsAddition; i++) {
            int sum = carry + (getMemorySpace().getBitForTestOnly(indexA) ? 1 : 0) + (getMemorySpace().getBitForTestOnly(indexB) ? 1 : 0);
            if (sum == 0) {
                targetAdditionResult.add(false);
                carry = 0;
            } else if (sum == 1) {
                targetAdditionResult.add(true);
                carry = 0;
            } else if (sum == 2) {
                targetAdditionResult.add(false);
                carry = 1;
            } else {
                targetAdditionResult.add(true);
                carry = 1;
            }
            indexA++;
            indexB++;
        }
    }

    public boolean isMetTargetResult() {
        int resultOffSet = _startA + 2 * _bitInterval;
        for (int i = 0; i < targetAdditionResult.size(); i++) {
            if (targetAdditionResult.get(i) != memorySpace.getBitForTestOnly(resultOffSet + i)) {
                return false;
            }
        }
        return true;
    }
}
