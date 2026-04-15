package project20280.tree;

import project20280.interfaces.Entry;
import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.Comparator;

public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

    public static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {
        protected static class BSTNode<E> extends Node<E> {
            int aux = 0;

            BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
                super(e, parent, leftChild, rightChild);
            }

            public int getAux() {
                return aux;
            }

            public void setAux(int value) {
                aux = value;
            }
        }

        public int getAux(Position<Entry<K, V>> p) {
            return ((BSTNode<Entry<K, V>>) p).getAux();
        }

        public void setAux(Position<Entry<K, V>> p, int value) {
            ((BSTNode<Entry<K, V>>) p).setAux(value);
        }

        @Override
        protected Node<Entry<K, V>> createNode(Entry<K, V> e, Node<Entry<K, V>> parent,
                                               Node<Entry<K, V>> left, Node<Entry<K, V>> right) {
            return new BSTNode<>(e, parent, left, right);
        }

        private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
            if (makeLeftChild) parent.setLeft(child);
            else parent.setRight(child);

            if (child != null) child.setParent(parent);
        }

        public void rotate(Position<Entry<K, V>> p) {
            Node<Entry<K, V>> x = validate(p);
            Node<Entry<K, V>> y = x.getParent();
            Node<Entry<K, V>> z = y.getParent();

            if (z == null) {
                root = x;
                x.setParent(null);
            } else {
                relink(z, x, y == z.getLeft());
            }

            if (x == y.getLeft()) {
                relink(y, x.getRight(), true);
                relink(x, y, false);
            } else {
                relink(y, x.getLeft(), false);
                relink(x, y, true);
            }
        }

        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
            Position<Entry<K, V>> y = parent(x);
            Position<Entry<K, V>> z = parent(y);

            if ((x == right(y)) == (y == right(z))) {
                rotate(y);
                return y;
            } else {
                rotate(x);
                rotate(x);
                return x;
            }
        }
    }

    public BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

    public TreeMap() {
        super();
        tree.addRoot(null);
    }

    public TreeMap(Comparator<K> comp) {
        super(comp);
        tree.addRoot(null);
    }

    @Override
    public int size() {
        return (tree.size() - 1) / 2;
    }

    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

    protected void rebalanceInsert(Position<Entry<K, V>> p) { }

    protected void rebalanceDelete(Position<Entry<K, V>> p) { }

    protected void rebalanceAccess(Position<Entry<K, V>> p) { }

    private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
        tree.set(p, entry);
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    public Position<Entry<K, V>> root() {
        return tree.root();
    }

    protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    protected boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    public boolean isExternal(Position<Entry<K, V>> p) {
        return p == null || p.getElement() == null;
    }

    public boolean isInternal(Position<Entry<K, V>> p) {
        return p != null && p.getElement() != null;
    }

    protected void set(Position<Entry<K, V>> p, Entry<K, V> e) {
        tree.set(p, e);
    }

    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }

    protected Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K key) {
        if (isExternal(p)) return p;

        int comp = compare(key, p.getElement().getKey());

        if (comp == 0) return p;
        else if (comp < 0) return treeSearch(left(p), key);
        else return treeSearch(right(p), key);
    }

    protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
        while (isInternal(p)) {
            if (isExternal(left(p))) return p;
            p = left(p);
        }
        return p;
    }

    protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
        while (isInternal(p)) {
            if (isExternal(right(p))) return p;
            p = right(p);
        }
        return p;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        Position<Entry<K, V>> p = treeSearch(root(), key);
        rebalanceAccess(p);

        if (isExternal(p)) return null;
        return p.getElement().getValue();
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        Entry<K, V> entry = new MapEntry<>(key, value);
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (isExternal(p)) {
            expandExternal(p, entry);
            rebalanceInsert(p);
            return null;
        } else {
            V old = p.getElement().getValue();
            set(p, entry);
            return old;
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (isExternal(p)) return null;

        V old = p.getElement().getValue();

        if (isInternal(left(p)) && isInternal(right(p))) {
            Position<Entry<K, V>> replacement = treeMin(right(p));
            set(p, replacement.getElement());
            p = replacement;
        }

        Position<Entry<K, V>> leaf = isExternal(left(p)) ? left(p) : right(p);
        Position<Entry<K, V>> sib = sibling(leaf);

        remove(leaf);
        remove(p);

        rebalanceDelete(sib);
        return old;
    }

    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty()) return null;
        return treeMin(root()).getElement();
    }

    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) return null;
        return treeMax(root()).getElement();
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (isInternal(p)) return p.getElement();

        while (!isRoot(p)) {
            if (p == left(parent(p))) return parent(p).getElement();
            p = parent(p);
        }
        return null;
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (isInternal(p)) return p.getElement();

        while (!isRoot(p)) {
            if (p == right(parent(p))) return parent(p).getElement();
            p = parent(p);
        }
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (isInternal(p) && isInternal(left(p)))
            return treeMax(left(p)).getElement();

        while (!isRoot(p)) {
            if (p == right(parent(p))) return parent(p).getElement();
            p = parent(p);
        }
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (isInternal(p) && isInternal(right(p)))
            return treeMin(right(p)).getElement();

        while (!isRoot(p)) {
            if (p == left(parent(p))) return parent(p).getElement();
            p = parent(p);
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());
        for (Position<Entry<K, V>> p : tree.inorder()) {
            if (isInternal(p)) buffer.add(p.getElement());
        }
        return buffer;
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();

        for (Entry<K, V> e : entrySet()) {
            if (compare(e.getKey(), fromKey) >= 0 && compare(e.getKey(), toKey) < 0) {
                buffer.add(e);
            }
        }
        return buffer;
    }

    @Override
    public Iterable<K> keySet() {
        ArrayList<K> buffer = new ArrayList<>();
        for (Entry<K, V> e : entrySet()) {
            buffer.add(e.getKey());
        }
        return buffer;
    }

    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    @Override
    public String toString() {
        return "";
    }

    protected void dump() {
        dumpRecurse(root(), 0);
    }

    private void dumpRecurse(Position<Entry<K, V>> p, int depth) {
        String indent = (depth == 0 ? "" : String.format("%" + (2 * depth) + "s", ""));
        if (isExternal(p)) {
            System.out.println(indent + "leaf");
        } else {
            System.out.println(indent + p.getElement());
            dumpRecurse(left(p), depth + 1);
            dumpRecurse(right(p), depth + 1);
        }
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>(this.tree);
        return btp.print();
    }
}