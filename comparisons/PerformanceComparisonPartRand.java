package project20280.tree.comparisons;
import project20280.tree.AVLTreeMap;
import project20280.tree.TreapMap;


import java.util.Random;
import java.util.TreeMap;

public class PerformanceComparisonPartRand {

    public static void main(String[] args) {

        Random rand = new Random();

        TreapMap<Integer, Integer> treap100 = new TreapMap<>();
        TreapMap<Integer, Integer> treap1000 = new TreapMap<>();
        TreapMap<Integer, Integer> treap10000 = new TreapMap<>();
        AVLTreeMap<Integer, Integer> avlTree = new AVLTreeMap<>();
        TreeMap<Integer, Integer> javaTree = new TreeMap<>();

        // Created 3 arrays of 3 different sizes
        int[] size1 = new int[100];
        int[] size2 = new int[1000];
        int[] size3 = new int[10000];

        String[] mode = {"build", "add", "search", "remove", "traverse"};

        for (int i = 0; i < size1.length; i++) {
            int binary = rand.nextInt(2); //dictate between whether it plugs a random or ordered value
            if (binary == 0){
                size1[i] = i; //inserts ordered value
            }
            else{
                size1[i] = rand.nextInt(10000); //inserts random value
            }
        }

        for (int i = 0; i < size2.length; i++) {
            int binary = rand.nextInt(2);
            if (binary == 0){
                size2[i] = i;
            }
            else{
                size2[i] = rand.nextInt(10000);
            }
        }

        for (int i = 0; i < size3.length; i++) {
            int binary = rand.nextInt(2);
            if (binary == 0){
                size3[i] = i;
            }
            else{
                size3[i] = rand.nextInt(size1.length/2, 10000);
            }
        }



        for(String currMode : mode){
            switch(currMode){
                case "build":
                    long startTreap = System.nanoTime();
                    for (int value : size1) {
                        treap100.put(value, value);
                    }
                    long endTreap = System.nanoTime();
                    System.out.println("TreapMap with size 100: " + (endTreap - startTreap) + " ns");

                    long startTreap2 = System.nanoTime();
                    for (int value : size2) {
                        treap1000.put(value, value);
                    }
                    long endTreap2 = System.nanoTime();
                    System.out.println("TreapMap with size 1000: " + (endTreap2 - startTreap2) + " ns");

                    long startTreap3 = System.nanoTime();
                    for (int value : size3) {
                        treap1000.put(value, value);
                    }
                    long endTreap3 = System.nanoTime();
                    System.out.println("TreapMap with size 10000: " + (endTreap3 - startTreap3) + " ns");
                    break;
                case "add":
                    break;
                case "search":
                    break;
                case "remove":
                    break;
                case "traverse":
                    break;
                default:
                    break;
            }


        }








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

    }
}
