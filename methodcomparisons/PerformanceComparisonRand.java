package project20280.tree.methodcomparisons;
import project20280.interfaces.Entry;
import project20280.tree.AVLTreeMap;
import project20280.tree.TreapMap;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class PerformanceComparisonRand {


    //add methods
    public static void buildTreeTreap(TreapMap<Integer, Integer> a, int[] b){
        long start = System.nanoTime();
        for (int value : b) {
            a.put(value, value);
        }
        long end = System.nanoTime();
        System.out.println("TreapMap with size " + b.length + ": " + (end - start) +  " ns");
    }

    public static void buildTreeAVLTreeMap(AVLTreeMap<Integer, Integer> a, int[] b){
        long start = System.nanoTime();
        for (int value : b) {
            a.put(value, value);
        }
        long end = System.nanoTime();
        System.out.println("AVLTreeMap with size " + b.length + ": " + (end - start) +  " ns");
    }

    public static void buildTreeJavaTreeMap(TreeMap<Integer, Integer> a, int[] b) {
        long start = System.nanoTime();
        for (int value : b) {
            a.put(value, value);
        }
        long end = System.nanoTime();
        System.out.println("JavaTreeMap with size " + b.length + ": " + (end - start) + " ns");
    }

    //search methods
    public static void searchTreap(TreapMap<Integer, Integer> a, int[] b) {
        long start = System.nanoTime();
        a.get(b[b.length/2]); //this will search for a value referenced in the middle of the array
        long end = System.nanoTime();
        System.out.println("Successful JavaTree Search with size " + b.length + ": " + (end - start) + " ns");

        start = System.nanoTime();
        a.get(-1); //this is a number that's not present in any of the trees, might be inconsistent for random.
        end = System.nanoTime();
        System.out.println("Failed Treap Search with size " + b.length + ": " + (end - start) + " ns");
    }

    public static void searchAVLTreeMap(AVLTreeMap<Integer, Integer> a, int[] b) {
        long start = System.nanoTime();
        a.get(b[b.length/2]);
        long end = System.nanoTime();
        System.out.println("Successful JavaTree Search with size " + b.length + ": " + (end - start) + " ns");

        start = System.nanoTime();
        a.get(-1);
        end = System.nanoTime();
        System.out.println("Failed Treap Search with size " + b.length + ": " + (end - start) + " ns");
    }

    public static void searchJavaTreeMap(TreeMap<Integer, Integer> a, int[] b) {
        long start = System.nanoTime();
        a.get(b[b.length/2]);
        long end = System.nanoTime();
        System.out.println("Successful JavaTree Search with size " + b.length + ": " + (end - start) + " ns");

        start = System.nanoTime();
        a.get(-1);
        end = System.nanoTime();
        System.out.println("Failed Treap Search with size " + b.length + ": " + (end - start) + " ns");
    }

    //traverse method
    public static void traverseTreap(TreapMap<Integer, Integer> a, int[] b) {
        long start = System.nanoTime();
        for (Entry<Integer, Integer> value : a.entrySet()) { //this should iterate through every element present, I used this code: https://www.geeksforgeeks.org/java/how-to-iterate-over-a-treemap-in-java/
            int i = 1;
        }
        long end = System.nanoTime();
        System.out.println("Traversed TreapMap with size " + b.length + ": " + (end - start) + " ns");
    }

    public static void traverseAVLTreeMap(AVLTreeMap<Integer, Integer> a, int[] b) {
        long start = System.nanoTime();
        for (Entry<Integer, Integer> value : a.entrySet()) { //this should iterate through every element present, I used this code: https://www.geeksforgeeks.org/java/how-to-iterate-over-a-treemap-in-java/
            int i = 1;
        }
        long end = System.nanoTime();
        System.out.println("Traversed TreapMap with size " + b.length + ": " + (end - start) + " ns");
    }

    public static void traverseJavaTree(TreeMap<Integer, Integer> a, int[] b){
        long start = System.nanoTime();
        for(Map.Entry<Integer, Integer> value : a.entrySet()){ //this should iterate through every element present, I used this code: https://www.geeksforgeeks.org/java/how-to-iterate-over-a-treemap-in-java/
            int i = 1;
        }
        long end = System.nanoTime();
        System.out.println("Traversed TreeMap with size " + b.length + ": " + (end - start) +  " ns");
    }


    //remove method
    public static void removeTreap(TreapMap<Integer, Integer> a, int[] b){
        long start = System.nanoTime();
        for (int i = b.length; i > 0; i--) { //the for loop uses the array as a reference to remove values
            a.remove(i);
        }
        long end = System.nanoTime();
        System.out.println("Removed TreapMap with size " + b.length + ": " + (end - start) +  " ns");
    }

    public static void removeAVLTreeMap(AVLTreeMap<Integer, Integer> a, int[] b){
        long start = System.nanoTime();
        for (int i = b.length; i > 0; i--) {
            a.remove(i);
        }
        long end = System.nanoTime();
        System.out.println("Removed AVLTreeMap with size " + b.length + ": " + (end - start) +  " ns");
    }

    public static void removeJavaTreeMap(TreeMap<Integer, Integer> a, int[] b) {
        long start = System.nanoTime();
        for (int i = b.length; i > 0; i--) {
            a.remove(i);
        }
        long end = System.nanoTime();
        System.out.println("Removed JavaTreeMap with size " + b.length + ": " + (end - start) + " ns");
    }



    public static void main(String[] args) {

        Random rand = new Random();

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
                size1[i] = rand.nextInt(10000); //inserts random value
        }

        for (int i = 0; i < size2.length; i++) {
                size2[i] = rand.nextInt(10000);

        }

        for (int i = 0; i < size3.length; i++) {
                size3[i] = rand.nextInt(size1.length/2, 10000);
        }


        //mode change, this for loop goes through each mode in order
        for(String currMode : mode){
            switch(currMode){
                case "add": //this builds the tree to begin our measurements.
                    buildTreeTreap(treap100, size1);
                    buildTreeTreap(treap1000, size2);
                    buildTreeTreap(treap10000, size3);
                    System.out.println();
                    buildTreeAVLTreeMap(avlTree100, size1);
                    buildTreeAVLTreeMap(avlTree1000, size2);
                    buildTreeAVLTreeMap(avlTree10000, size3);
                    System.out.println();
                    buildTreeJavaTreeMap(javaTree100, size1);
                    buildTreeJavaTreeMap(javaTree1000, size2);
                    buildTreeJavaTreeMap(javaTree10000, size3);
                    System.out.println("\n-----\n");
                    break;
                case "search": //search for certain values within the trees, measure both success and unsuccess clicks
                    searchTreap(treap100, size1);
                    searchTreap(treap1000, size2);
                    searchTreap(treap1000, size3);
                    System.out.println();
                    searchAVLTreeMap(avlTree100, size1);
                    searchAVLTreeMap(avlTree1000, size2);
                    searchAVLTreeMap(avlTree10000, size3);
                    System.out.println();
                    searchJavaTreeMap(javaTree100, size1);
                    searchJavaTreeMap(javaTree1000, size2);
                    searchJavaTreeMap(javaTree10000, size3);
                    System.out.println("\n-----\n");
                    break;
                case "traverse": //go through the entire tree and declare how long it takes to traverse
                    traverseTreap(treap100, size1);
                    traverseTreap(treap1000, size2);
                    traverseTreap(treap1000, size3);
                    System.out.println();
                    traverseAVLTreeMap(avlTree100, size1);
                    traverseAVLTreeMap(avlTree1000, size2);
                    traverseAVLTreeMap(avlTree10000, size3);
                    System.out.println();
                    traverseJavaTree(javaTree100, size1);
                    traverseJavaTree(javaTree1000, size2);
                    traverseJavaTree(javaTree10000, size3);
                    System.out.println("\n-----\n");
                    break;
                case "remove": //measure how long it takes to remove the entire tree, this is the last switch for an obvious
                    removeTreap(treap100, size1);
                    removeTreap(treap1000, size2);
                    removeTreap(treap10000, size3);
                    System.out.println();
                    removeAVLTreeMap(avlTree100, size1);
                    removeAVLTreeMap(avlTree1000, size2);
                    removeAVLTreeMap(avlTree10000, size3);
                    System.out.println();
                    removeJavaTreeMap(javaTree100, size1);
                    removeJavaTreeMap(javaTree1000, size2);
                    removeJavaTreeMap(javaTree10000, size3);
                    break;
                default: //if none of these strings are met, the program will automatically end via default
                    break;
            }
        }
    }
}
