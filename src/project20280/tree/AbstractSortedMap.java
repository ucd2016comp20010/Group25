package project20280.tree;

import project20280.interfaces.AbstractMap;
import project20280.interfaces.SortedMap;

import java.util.Comparator;

public abstract class AbstractSortedMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V> {

    private final Comparator<K> comp;

    protected AbstractSortedMap() {
        this.comp = new DefaultComparator<>();
    }

    protected AbstractSortedMap(Comparator<K> c) {
        this.comp = c;
    }

    protected int compare(K a, K b) {
        return comp.compare(a, b);
    }

    protected static class DefaultComparator<E> implements Comparator<E> {
        @SuppressWarnings("unchecked")
        public int compare(E a, E b) throws ClassCastException {
            return ((Comparable<E>) a).compareTo(b);
        }
    }
}