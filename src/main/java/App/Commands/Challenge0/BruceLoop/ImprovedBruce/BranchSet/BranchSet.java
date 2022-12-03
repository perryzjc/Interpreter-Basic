package App.Commands.Challenge0.BruceLoop.ImprovedBruce.BranchSet;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public class BranchSet implements Serializable,Iterable<Branch> {
    public static final File BRANCH_SET_DIR = new File("src/main/java/App/Commands/Challenge0/BruceLoop/ImprovedBruce/BranchSet");
    public static final String POSTFIX = ".bset";

    private HashSet<Branch> branchSet;

    public BranchSet() {
        branchSet = new HashSet<>();
    }

    public BranchSet(File file) {
        BranchSet branchSet = (BranchSet) Utils.readObject(file, BranchSet.class);
        this.branchSet = branchSet.branchSet;
    }

    public BranchSet(BranchSet branchSet) {
        this.branchSet = new HashSet<>(branchSet.branchSet);
    }

    public void add(Branch branch) {
        branchSet.add(new Branch(branch));
    }

    public boolean contains(Branch branch) {
        return branchSet.contains(branch);
    }

    public void serialize(int numOfCommands) {
        String filename = numOfCommands + POSTFIX;
        File file = new File(BRANCH_SET_DIR, filename);
        Utils.writeObject(file, this);
    }

    public static BranchSet deserialize(int numOfCommands) {
        String filename = numOfCommands + POSTFIX;
        File file = new File(BRANCH_SET_DIR, filename);
        return (BranchSet) Utils.readObject(file, BranchSet.class);
    }

    @Override
    public Iterator<Branch> iterator() {
        return branchSet.iterator();
    }
}
