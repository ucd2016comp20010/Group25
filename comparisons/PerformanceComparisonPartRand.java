package project20280.tree.comparisons;
import project20280.tree.AVLTreeMap;
import project20280.tree.TreapMap;

import java.util.Random;

public class PerformanceComparisonPartRand {

    public static void main(String[] args) {

        Random rand = new Random();

        TreapMap<Integer, Integer> treap = new TreapMap<>();
        AVLTreeMap<Integer, Integer> avlTree = new AVLTreeMap<>();

        // Created 3 arrays of 3 different sizes
        int[] size1 = new int[100];
        int[] size2 = new int[1000];
        int[] size3 = new int[10000];

        for (int i = 0; i < size1.length/2; i++) {
            size1[i] = i; // sorted int in ascending order
        }

        for (int i = size1.length/2; i < size1.length; i++) {
            size1[i] = rand.nextInt(size1.length/2, 10000);  // randomise the rest, limited between the size of array to 10000 to avoid duplicates
        }

        for (int i = 0; i < size2.length/2; i++) {
            size2[i] = i; // sorted int in ascending order
        }

        for (int i = size2.length/2; i < size2.length; i++) {
            size2[i] = rand.nextInt(size2.length/2, 10000);
        }

        for (int i = 0; i < size3.length/2; i++) {
            size3[i] = i; // sorted int in ascending order
        }

        for (int i = size3.length/2; i < size3.length; i++) {
            size3[i] = rand.nextInt(size3.length/2, 10000);
        }




        long startTreap = System.nanoTime();
        for (int j = 0; j < size1.length; j++) {
            int value = size1[j];
            treap.put(value, value);
        }
        long endTreap = System.nanoTime();
        System.out.println("TreapMap with size 100: " + (endTreap - startTreap) + " ns");

        long startTreap2 = System.nanoTime();
        for (int j = 0; j < size2.length; j++) {
            int value = size2[j];
            treap.put(value, value);
        }
        long endTreap2 = System.nanoTime();
        System.out.println("TreapMap with size 1000: " + (endTreap2 - startTreap2) + " ns");

        long startTreap3 = System.nanoTime();
        for (int j = 0; j < size3.length; j++) {
            int value = size3[j];
            treap.put(value, value);
        }
        long endTreap3 = System.nanoTime();
        System.out.println("TreapMap with size 10000: " + (endTreap3 - startTreap3) + " ns");


        long startAVLTree = System.nanoTime();
        for (int j = 0; j < size1.length; j++) {
            int value = size1[j];
            avlTree.put(value, value);
        }
        long endAVLTree = System.nanoTime();
        System.out.println("AVLTree with size 100: " + (endAVLTree - startAVLTree) + " ns");

        long startAVLTree2 = System.nanoTime();
        for (int j = 0; j < size2.length; j++) {
            int value = size2[j];
            avlTree.put(value, value);
        }
        long endAVLTree2 = System.nanoTime();
        System.out.println("AVLTree with size 1000: " + (endAVLTree2 - startAVLTree2) + " ns");

        long startAVLTree3 = System.nanoTime();
        for (int j = 0; j < size3.length; j++) {
            int value = size3[j];
            avlTree.put(value, value);
        }
        long endAVLTree3 = System.nanoTime();
        System.out.println("AVLTree with size 10000: " + (endAVLTree3 - startAVLTree3) + " ns");



        for (int i = 0; i < size1.length; i++) {
            size1[i] = i; // sorted int in ascending order
        }

        for (int i = 0; i < size2.length; i++) {
            size2[i] = i;
        }

        for (int i = 0; i < size3.length; i++) {
            size3[i] = i;
        }






    }
}
