package project20280.tree.comparisons;
import project20280.tree.AVLTreeMap;
import project20280.tree.TreapMap;

import java.util.Map;
import java.util.TreeMap;

public class PerformanceComparisonSortedAsc {

    public static void main(String[] args) {

        long startTimer, endTimer;

        //three different trees of each type to demonstrate input response time.
        TreapMap<Integer, Integer> treap100 = new TreapMap<>();
        TreapMap<Integer, Integer> treap1000 = new TreapMap<>();
        TreapMap<Integer, Integer> treap10000 = new TreapMap<>();

        AVLTreeMap<Integer, Integer> avlTree100 = new AVLTreeMap<>();
        AVLTreeMap<Integer, Integer> avlTree1000 = new AVLTreeMap<>();
        AVLTreeMap<Integer, Integer> avlTree10000 = new AVLTreeMap<>();

        TreeMap<Integer, Integer> javaTree100 = new TreeMap<>();
        TreeMap<Integer, Integer> javaTree1000 = new TreeMap<>();
        TreeMap<Integer, Integer> javaTree10000 = new TreeMap<>();

        // Created 3 arrays of 3 different sizes
        int[] size1 = new int[100];
        int[] size2 = new int[1000];
        int[] size3 = new int[10000];

        //mode used for switch case, written in string to convey what they do
        String[] mode = {"add", "search", "traverse", "remove"};

        for (int i = 0; i < size1.length; i++) {
            size1[i] = i; // sorted int in ascending order
        }

        for (int i = 0; i < size2.length; i++) {
            size2[i] = i;
        }

        for (int i = 0; i < size3.length; i++) {
            size3[i] = i;
        }

        //mode change, this for loop goes through each mode in order
        for(String currMode : mode){
            switch(currMode){
                case "add": //this builds the tree to begin our measurements.
                    startTimer = System.nanoTime();
                    for (int value : size1) {
                        treap100.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("TreapMap with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime(); //timer is declared again to reset it
                    for (int value : size2) {
                        treap1000.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("TreapMap with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int value : size3) {
                        treap10000.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("TreapMap with size 10000: " + (endTimer - startTimer) + " ns\n");


                    //AVLTree
                    startTimer = System.nanoTime();
                    for (int value : size1) {
                        avlTree100.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("AVLTree with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime(); //timer is declared again to reset it
                    for (int value : size2) {
                        avlTree1000.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("AVLTree with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int value : size3) {
                        avlTree10000.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("AVLTree with size 10000: " + (endTimer - startTimer) + " ns\n");


                    //JavaTree
                    startTimer = System.nanoTime();
                    for (int value : size1) {
                        javaTree100.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("JavaTree with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime(); //timer is declared again to reset it
                    for (int value : size2) {
                        javaTree1000.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("JavaTree with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int value : size3) {
                        javaTree10000.put(value, value);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("JavaTree with size 10000: " + (endTimer - startTimer) + " ns\n\n----\n");

                    break;
                case "search": //search for certain values within the trees, measure both success and unsuccess clicks
                    //successful search, treap
                    startTimer = System.nanoTime();
                    treap100.get(size1[50]); //this will search for a value referenced in the middle of the array
                    endTimer = System.nanoTime();
                    System.out.println("Successful Treap Search with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    treap1000.get(size2[500]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful Treap Search with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    treap10000.get(size3[5000]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful Treap Search with size 10000: " + (endTimer - startTimer) + " ns\n");

                    //avltree
                    startTimer = System.nanoTime();
                    avlTree100.get(size1[50]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful AVLTree Search with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    avlTree1000.get(size2[500]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful AVLTree Search with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    avlTree10000.get(size3[5000]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful AVLTree Search with size 10000: " + (endTimer - startTimer) + " ns\n");

                    //javatree
                    startTimer = System.nanoTime();
                    javaTree100.get(size1[50]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful JavaTree Search with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    javaTree1000.get(size2[500]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful JavaTree Search with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    javaTree10000.get(size3[5000]);
                    endTimer = System.nanoTime();
                    System.out.println("Successful JavaTree Search with size 10000: " + (endTimer - startTimer) + " ns\n");


                    //failed search
                    startTimer = System.nanoTime();
                    treap100.get(-1); //this will search for a value referenced in the middle of the array
                    endTimer = System.nanoTime();
                    System.out.println("Failed Treap Search with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    treap1000.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed Treap Search with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    treap10000.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed Treap Search with size 10000: " + (endTimer - startTimer) + " ns\n");

                    //avltree
                    startTimer = System.nanoTime();
                    avlTree100.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed AVLTree Search with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    avlTree1000.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed AVLTree Search with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    avlTree10000.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed AVLTree Search with size 10000: " + (endTimer - startTimer) + " ns\n");

                    //javatree
                    startTimer = System.nanoTime();
                    javaTree100.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed JavaTree Search with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    javaTree1000.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed JavaTree Search with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    javaTree10000.get(-1);
                    endTimer = System.nanoTime();
                    System.out.println("Failed JavaTree Search with size 10000: " + (endTimer - startTimer) + " ns\n\n----\n");

                    break;
                case "traverse": //go through the entire tree and declare how long it takes to traverse
                    break;
                case "remove": //measure how long it takes to remove the entire tree, this is the last switch for an obvious
                    //Treap
                    startTimer = System.nanoTime();
                    for (int i = size1.length; i > 0; i--) { //the for loop uses the array as a reference to remove values
                        treap100.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed TreapMap with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int i = size2.length; i > 0; i--) {
                        treap1000.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed TreapMap with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int i = size3.length; i > 0; i--) {
                        treap10000.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed TreapMap with size 10000: " + (endTimer - startTimer) + " ns\n");

                    /*
                    startTimer = System.nanoTime();
                    for (int i = size1.length; i > 0; i--) {
                        avlTree100.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed AVLTree with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int i = size2.length; i > 0; i--) {
                        avlTree1000.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed AVLTree with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int i = size3.length; i > 0; i--) {
                        avlTree10000.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed AVLTree with size 10000: " + (endTimer - startTimer) + " ns\n");
                    */

                    //JavaTreeMa[
                    startTimer = System.nanoTime();
                    for (int i = size1.length; i > 0; i--) { //the for loop uses the array as a reference to remove values
                        javaTree100.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed JavaTree with size 100: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int i = size2.length; i > 0; i--) {
                        javaTree1000.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed JavaTree with size 1000: " + (endTimer - startTimer) + " ns");

                    startTimer = System.nanoTime();
                    for (int i = size3.length; i > 0; i--) {
                        javaTree10000.remove(i);
                    }
                    endTimer = System.nanoTime();
                    System.out.println("Removed JavaTree with size 10000: " + (endTimer - startTimer) + " ns");

                    break;
                default: //if none of these strings are met, the program will automatically end via default
                    break;
            }
        }
    }
}
