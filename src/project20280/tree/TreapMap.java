package project20280.tree;

import project20280.interfaces.Entry;
import project20280.interfaces.Position;

import java.util.Comparator;
import java.util.Random;

public class TreapMap<K, V> extends TreeMap<K, V> {

    private final Random random = new Random();

    public TreapMap() {
        super();
    }

    public TreapMap(Comparator<K> comp) {
        super(comp);
    }

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        tree.setAux(p, random.nextInt());

        while (!isRoot(p) && tree.getAux(p) > tree.getAux(parent(p))) {
            rotate(p);
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (isExternal(p)) return null;

        V old = p.getElement().getValue();

        while (isInternal(left(p)) || isInternal(right(p))) {
            Position<Entry<K, V>> child;

            if (isExternal(left(p))) {
                child = right(p);
            } else if (isExternal(right(p))) {
                child = left(p);
            } else if (tree.getAux(left(p)) > tree.getAux(right(p))) {
                child = left(p);
            } else {
                child = right(p);
            }

            rotate(child);
        }

        Position<Entry<K, V>> leaf = isExternal(left(p)) ? left(p) : right(p);
        Position<Entry<K, V>> sib = sibling(leaf);

        remove(leaf);
        remove(p);

        rebalanceDelete(sib);

        return old;
    }
}