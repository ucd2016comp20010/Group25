package project20280;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project20280.interfaces.Entry;
import project20280.interfaces.Position;
import project20280.tree.TreapMap;

public class TreapMapTest {

    private TreapMap<Integer, String> treap;

    @BeforeEach
    public void setUp() {
        treap = new TreapMap<>();
    }

    // To test the insertion logic. The key is 10 and the value is A.
    @Test
    public void testPutAndGet() {
        treap.put(10, "A");
        assertEquals("A", treap.get(10));
        assertEquals(1, treap.size());
    }

    // Inserts two nodes and remove one of them. Since a rotation
    // may happen, we need to check whether the key of the removed
    // node is done and the size has updates
    @Test
    public void testRemove() {
        treap.put(10, "A");
        treap.put(20, "B");
        assertEquals("A", treap.remove(10));
        assertNull(treap.get(10));
        assertEquals(1, treap.size());
    }

    // Checks the BST property of the Treap. BST doesn't need
    // the priority aspect to function, the bigger node should be
    // in its create place, and the smaller one.
    @Test
    public void testNavigation() {
        treap.put(50, "Root");
        treap.put(10, "Small");
        treap.put(90, "Large");
        assertEquals(10, treap.firstEntry().getKey());
        assertEquals(90, treap.lastEntry().getKey());
    }

    @Test
    public void testSubMap() {
        treap.put(10, "V1");
        treap.put(20, "V2");
        treap.put(30, "V3");
        int count = 0;
        for (Entry<Integer, String> e : treap.subMap(15, 35)) {
            count++;
        }
        assertEquals(2, count);
    }

    // Check if the structure property of a Treap is maintained
    @Test
    public void testStructuralProperties() {
        // Insert random data
        int[] keys = {45, 12, 67, 34, 89, 23, 56};
        for (int k : keys) treap.put(k, "val");

        // Check BST Property
        int lastKey = Integer.MIN_VALUE;
        for (Entry<Integer, String> e : treap.entrySet()) {
            assertTrue(e.getKey() >= lastKey);
            lastKey = e.getKey();
        }

        // Check Heap Property (Parent priority >= Child priority)
        checkHeap(treap.root());
    }

    // Recursive helper to check the Heap property
    private void checkHeap(Position<Entry<Integer, String>> p) {
        if (treap.isExternal(p)) return;
        int pPri = treap.tree.getAux(p);

        if (treap.isInternal(treap.left(p))) {
            assertTrue(pPri >= treap.tree.getAux(treap.left(p)), "Left child has higher priority than parent");
            checkHeap(treap.left(p));
        }
        if (treap.isInternal(treap.right(p))) {
            assertTrue(pPri >= treap.tree.getAux(treap.right(p)), "Right child has higher priority than parent");
            checkHeap(treap.right(p));
        }
    }
}