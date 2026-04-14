package project20280.tree;

import project20280.interfaces.BinaryTree;
import project20280.interfaces.Position;

public class BinaryTreePrinter<E> {

    private final BinaryTree<E> tree;

    public BinaryTreePrinter(BinaryTree<E> tree) {
        this.tree = tree;
    }

    public String print() {
        if (tree == null || tree.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        printRecursive(tree.root(), 0, sb);
        return sb.toString();
    }

    private void printRecursive(Position<E> p, int depth, StringBuilder sb) {
        if (p == null) return;

        Position<E> right = tree.right(p);
        if (right != null) {
            printRecursive(right, depth + 1, sb);
        }

        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        sb.append(p.getElement()).append("\n");

        Position<E> left = tree.left(p);
        if (left != null) {
            printRecursive(left, depth + 1, sb);
        }
    }
}