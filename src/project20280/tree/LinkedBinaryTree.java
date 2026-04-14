package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();

    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() {
    }

    public void construct(E[] inorder, E[] preorder) {
        if (inorder == null || preorder == null)
            throw new IllegalArgumentException("Arrays must not be null");
        if (inorder.length != preorder.length)
            throw new IllegalArgumentException("inorder and preorder must have same length");

        size = 0;
        if (inorder.length == 0) {
            root = null;
            return;
        }

        Map<E, Integer> inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            if (inIndex.put(inorder[i], i) != null)
                throw new IllegalArgumentException("Duplicate keys in inorder: " + inorder[i]);
        }

        int[] prePos = {0};
        root = buildFromInPre(preorder, 0, inorder.length - 1, inIndex, prePos, null);

        if (prePos[0] != preorder.length)
            throw new IllegalArgumentException("Invalid traversals: not all preorder elements were used");
    }

    public List<List<E>> rootToLeafPaths() {
        List<List<E>> res = new ArrayList<>();
        if (isEmpty()) return res;

        List<E> path = new ArrayList<>();
        rootToLeafPaths(root(), path, res);
        return res;
    }

    private void rootToLeafPaths(Position<E> p, List<E> path, List<List<E>> res) {
        path.add(p.getElement());

        if (isExternal(p)) {
            res.add(new ArrayList<>(path));
        } else {
            if (left(p) != null) rootToLeafPaths(left(p), path, res);
            if (right(p) != null) rootToLeafPaths(right(p), path, res);
        }

        path.remove(path.size() - 1);
    }

    private Node<E> buildFromInPre(E[] preorder, int inL, int inR,
                                   Map<E, Integer> inIndex, int[] prePos,
                                   Node<E> parent) {
        if (inL > inR) return null;

        if (prePos[0] >= preorder.length)
            throw new IllegalArgumentException("Invalid traversals: preorder ended early");

        E rootVal = preorder[prePos[0]++];
        Integer k = inIndex.get(rootVal);
        if (k == null)
            throw new IllegalArgumentException("Value from preorder not found in inorder: " + rootVal);
        if (k < inL || k > inR)
            throw new IllegalArgumentException("Invalid traversals: value out of current inorder range: " + rootVal);

        Node<E> node = createNode(rootVal, parent, null, null);
        size++;

        node.setLeft(buildFromInPre(preorder, inL, k - 1, inIndex, prePos, node));
        node.setRight(buildFromInPre(preorder, k + 1, inR, inIndex, prePos, node));
        return node;
    }

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    public int height() {
        if (isEmpty()) return 0;
        return height(root());
    }

    private int height(Position<E> p) {
        if (isExternal(p)) return 0;
        int h = 0;
        for (Position<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    private int diameter = 0;

    public int diameter() {
        diameter = 0;
        diameterHeight(root());
        return diameter;
    }

    private int diameterHeight(Position<E> p) {
        if (p == null) return -1;

        int hl = -1;
        int hr = -1;

        if (left(p) != null) hl = diameterHeight(left(p));
        if (right(p) != null) hr = diameterHeight(right(p));

        diameter = Math.max(diameter, hl + hr + 2);
        return 1 + Math.max(hl, hr);
    }

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;

        Integer treeSize = last - first + 1;
        Integer leftCount = rnd.nextInt(treeSize);
        Node<T> root = new Node<>((T) (Integer) (first + leftCount), parent, null, null);
        root.setLeft(randomTree(root, first, first + leftCount - 1));
        root.setRight(randomTree(root, first + leftCount + 1, last));
        return root;
    }

    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<>(e, parent, left, right);
    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node) throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return validate(p).getParent();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return validate(p).getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return validate(p).getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty())
            throw new IllegalStateException("Tree is not empty");

        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        if (isEmpty()) {
            addRoot(e);
        } else {
            addRecursive(root, e);
        }
    }

    private Node<E> addRecursive(Node<E> p, E e) {
        if (p == null) return null;
        if (!(e instanceof Comparable)) return null;

        @SuppressWarnings("unchecked")
        Comparable<E> cmp = (Comparable<E>) e;

        if (cmp.compareTo(p.getElement()) <= 0) {
            if (p.getLeft() == null) {
                Node<E> child = createNode(e, p, null, null);
                p.setLeft(child);
                size++;
                return child;
            }
            return addRecursive(p.getLeft(), e);
        } else {
            if (p.getRight() == null) {
                Node<E> child = createNode(e, p, null, null);
                p.setRight(child);
                size++;
                return child;
            }
            return addRecursive(p.getRight(), e);
        }
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = validate(p);
        if (n.getLeft() != null)
            throw new IllegalArgumentException("already has a left child");

        Node<E> child = createNode(e, n, null, null);
        n.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = validate(p);
        if (n.getRight() != null)
            throw new IllegalArgumentException("already has a right child");

        Node<E> child = createNode(e, n, null, null);
        n.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)
            throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p))
            throw new IllegalArgumentException("p must be a leaf");

        if (t1 != null && !t1.isEmpty()) {
            node.setLeft(t1.root);
            t1.root.setParent(node);
            size += t1.size;
            t1.root = null;
            t1.size = 0;
        }

        if (t2 != null && !t2.isEmpty()) {
            node.setRight(t2.root);
            t2.root.setParent(node);
            size += t2.size;
            t2.root = null;
            t2.size = 0;
        }
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);

        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");

        Node<E> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();

        if (child != null)
            child.setParent(node.getParent());

        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (parent.getLeft() == node)
                parent.setLeft(child);
            else
                parent.setRight(child);
        }

        size--;
        node.setParent(node);
        return node.getElement();
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        size = 0;
        root = createLevelOrderHelper(l, null, 0);
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> l, Node<E> parent, int i) {
        if (i < 0 || i >= l.size()) return null;

        E val = l.get(i);
        if (val == null) return null;

        Node<E> node = createNode(val, parent, null, null);
        size++;

        node.setLeft(createLevelOrderHelper(l, node, 2 * i + 1));
        node.setRight(createLevelOrderHelper(l, node, 2 * i + 2));
        return node;
    }

    public void createLevelOrder(E[] arr) {
        size = 0;
        root = createLevelOrderHelper(arr, null, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> parent, int i) {
        if (i >= arr.length) return null;

        E val = arr[i];
        if (val == null) return null;

        Node<E> node = createNode(val, parent, null, null);
        size++;

        node.setLeft(createLevelOrderHelper(arr, node, 2 * i + 1));
        node.setRight(createLevelOrderHelper(arr, node, 2 * i + 2));
        return node;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        @Override
        public String toString() {
            return element == null ? "\u29B0" : element.toString();
        }
    }
}