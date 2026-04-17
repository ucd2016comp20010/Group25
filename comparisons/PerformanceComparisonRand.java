package project20280.tree.comparisons;
import project20280.tree.AVLTreeMap;
import project20280.tree.TreapMap;

import java.util.Random;
import java.util.TreeMap;

public class PerformanceComparisonRand {

    static final int RUNS = 100;

    public static void main(String[] args) {

        Random rand = new Random();
        long startTimer, endTimer, total;

        TreapMap<Integer, Integer> treap100 = new TreapMap<>();
        TreapMap<Integer, Integer> treap1000 = new TreapMap<>();
        TreapMap<Integer, Integer> treap10000 = new TreapMap<>();

        AVLTreeMap<Integer, Integer> avlTree100 = new AVLTreeMap<>();
        AVLTreeMap<Integer, Integer> avlTree1000 = new AVLTreeMap<>();
        AVLTreeMap<Integer, Integer> avlTree10000 = new AVLTreeMap<>();

        TreeMap<Integer, Integer> javaTree100 = new TreeMap<>();
        TreeMap<Integer, Integer> javaTree1000 = new TreeMap<>();
        TreeMap<Integer, Integer> javaTree10000 = new TreeMap<>();

        int[] size1 = new int[100];
        int[] size2 = new int[1000];
        int[] size3 = new int[10000];

        String[] mode = {"add", "search", "traverse", "remove"};

        for (int i = 0; i < size1.length; i++) size1[i] = rand.nextInt(10000);
        for (int i = 0; i < size2.length; i++) size2[i] = rand.nextInt(10000);
        for (int i = 0; i < size3.length; i++) size3[i] = rand.nextInt(10000);

        for (String currMode : mode) {
            switch (currMode) {
                case "add":
                    // --- TreapMap ---
                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreapMap<Integer, Integer> temp = new TreapMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size1) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("TreapMap with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreapMap<Integer, Integer> temp = new TreapMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size2) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("TreapMap with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreapMap<Integer, Integer> temp = new TreapMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size3) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("TreapMap with size 10000: " + (total / RUNS) + " ns\n");

                    // --- AVLTree ---
                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        AVLTreeMap<Integer, Integer> temp = new AVLTreeMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size1) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("AVLTree with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        AVLTreeMap<Integer, Integer> temp = new AVLTreeMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size2) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("AVLTree with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        AVLTreeMap<Integer, Integer> temp = new AVLTreeMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size3) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("AVLTree with size 10000: " + (total / RUNS) + " ns\n");

                    // --- JavaTree ---
                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreeMap<Integer, Integer> temp = new TreeMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size1) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("JavaTree with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreeMap<Integer, Integer> temp = new TreeMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size2) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("JavaTree with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreeMap<Integer, Integer> temp = new TreeMap<>();
                        startTimer = System.nanoTime();
                        for (int v : size3) temp.put(v, v);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("JavaTree with size 10000: " + (total / RUNS) + " ns\n\n----\n");

                    // Populate main trees for search phase
                    for (int v : size1) { treap100.put(v, v); avlTree100.put(v, v); javaTree100.put(v, v); }
                    for (int v : size2) { treap1000.put(v, v); avlTree1000.put(v, v); javaTree1000.put(v, v); }
                    for (int v : size3) { treap10000.put(v, v); avlTree10000.put(v, v); javaTree10000.put(v, v); }
                    break;

                case "search":
                    // Successful searches
                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); treap100.get(size1[50]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful Treap Search with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); treap1000.get(size2[500]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful Treap Search with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); treap10000.get(size3[5000]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful Treap Search with size 10000: " + (total / RUNS) + " ns\n");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); avlTree100.get(size1[50]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful AVLTree Search with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); avlTree1000.get(size2[500]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful AVLTree Search with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); avlTree10000.get(size3[5000]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful AVLTree Search with size 10000: " + (total / RUNS) + " ns\n");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); javaTree100.get(size1[50]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful JavaTree Search with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); javaTree1000.get(size2[500]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful JavaTree Search with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); javaTree10000.get(size3[5000]); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Successful JavaTree Search with size 10000: " + (total / RUNS) + " ns\n\n");

                    // Failed searches
                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); treap100.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed Treap Search with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); treap1000.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed Treap Search with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); treap10000.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed Treap Search with size 10000: " + (total / RUNS) + " ns\n");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); avlTree100.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed AVLTree Search with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); avlTree1000.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed AVLTree Search with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); avlTree10000.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed AVLTree Search with size 10000: " + (total / RUNS) + " ns\n");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); javaTree100.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed JavaTree Search with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); javaTree1000.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed JavaTree Search with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) { startTimer = System.nanoTime(); javaTree10000.get(-1); endTimer = System.nanoTime(); total += endTimer - startTimer; }
                    System.out.println("Failed JavaTree Search with size 10000: " + (total / RUNS) + " ns\n\n----\n");
                    break;

                case "traverse":
                    break;

                case "remove":
                    // --- TreapMap ---
                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreapMap<Integer, Integer> temp = new TreapMap<>();
                        for (int v : size1) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size1.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed TreapMap with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreapMap<Integer, Integer> temp = new TreapMap<>();
                        for (int v : size2) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size2.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed TreapMap with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreapMap<Integer, Integer> temp = new TreapMap<>();
                        for (int v : size3) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size3.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed TreapMap with size 10000: " + (total / RUNS) + " ns\n");

                    // --- AVLTree ---
                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        AVLTreeMap<Integer, Integer> temp = new AVLTreeMap<>();
                        for (int v : size1) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size1.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed AVLTree with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        AVLTreeMap<Integer, Integer> temp = new AVLTreeMap<>();
                        for (int v : size2) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size2.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed AVLTree with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        AVLTreeMap<Integer, Integer> temp = new AVLTreeMap<>();
                        for (int v : size3) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size3.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed AVLTree with size 10000: " + (total / RUNS) + " ns\n");

                    // --- JavaTree ---
                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreeMap<Integer, Integer> temp = new TreeMap<>();
                        for (int v : size1) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size1.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed JavaTree with size 100: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreeMap<Integer, Integer> temp = new TreeMap<>();
                        for (int v : size2) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size2.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed JavaTree with size 1000: " + (total / RUNS) + " ns");

                    total = 0;
                    for (int run = 0; run < RUNS; run++) {
                        TreeMap<Integer, Integer> temp = new TreeMap<>();
                        for (int v : size3) temp.put(v, v);
                        startTimer = System.nanoTime();
                        for (int i = size3.length; i > 0; i--) temp.remove(i);
                        endTimer = System.nanoTime();
                        total += endTimer - startTimer;
                    }
                    System.out.println("Removed JavaTree with size 10000: " + (total / RUNS) + " ns");
                    break;

                default:
                    break;
            }
        }
    }
}
