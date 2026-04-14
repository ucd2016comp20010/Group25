package project20280;

import project20280.tree.TreapMap;

public class Main {
    public static void main(String[] args) {
        TreapMap<Integer, String> treap = new TreapMap<>();

        treap.put(10, "ten");
        treap.put(5, "five");
        treap.put(20, "twenty");
        treap.put(15, "fifteen");
        treap.put(3, "three");
        treap.put(7, "seven");

        System.out.println("Keys: " + treap.keySet());
        System.out.println("Tree:");
        System.out.println(treap.toBinaryTreeString());

        treap.remove(10);

        System.out.println("After removing 10:");
        System.out.println(treap.toBinaryTreeString());
    }
}