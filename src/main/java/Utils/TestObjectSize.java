package Utils;

import App.Branch;
import App.MemorySpace;
import App.Pointer;
import App.Store;

import java.util.BitSet;

/**
 * @source: https://www.baeldung.com/java-boolean-array-bitset-performance
 * the performance of BitSet is better than boolean[]
 */
public class TestObjectSize {
    public static void main(String[] args) throws IllegalAccessException {
        final ClassIntrospector ci = new ClassIntrospector();

        ObjectInfo res;

//        ObjectA a = new ObjectA();
//        a.str = "122193891283129839123";
//        a.obj = new ObjectB();
//        res = ci.introspect(a);
//        System.out.println( res.getDeepSize());

        Branch b = new Branch(new MemorySpace(32), new Pointer(0), new Store(true));
        res = ci.introspect(b);
        System.out.println( res.getDeepSize());

        res = ci.introspect(new LogicalEquivalenceStruct());
        System.out.println( res.getDeepSize());
//        res = ci.introspect(new boolean[4]);

        res = ci.introspect(new BitSet(32));
        System.out.println( res.getDeepSize());
    }

    private static class ObjectA {
        String str;  // 4
        int i1; // 4
        byte b1; // 1
        byte b2; // 1
        int i2;  // 4
        ObjectB obj; //4
        byte b3;  // 1
        long l1; // 8
        byte ba[] = new byte[11];
    }

    private static class ObjectB {
        String str;  // 4
        int i1; // 4
        byte b1; // 1
        byte b2; // 1
        int i2;  // 4
        ObjectB obj; //4
        byte b3;  // 1
    }

    private static class LogicalEquivalenceStruct {
//        boolean ba[] = new boolean[34]; //capacity change when 9, increase by 8
//        long l1; // 8
        BitSet b1 =  new BitSet(32);
        BitSet b2 =  new BitSet(32);
    }
}
