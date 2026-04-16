package project20280.tree.comparisons;
import project20280.tree.AVLTreeMap;
import project20280.tree.TreapMap;


import java.util.Random;
import java.util.TreeMap;

public class PerformanceComparisonPartRand {

    public static void main(String[] args) {

        Random rand = new Random();
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
        String[] mode = {"add", "remove", "search", "traverse"};

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
                    System.out.println("JavaTree with size 10000: " + (endTimer - startTimer) + " ns");

                    break;
                case "search": //search for certain values within the trees, measure both success and unsuccess clicks
                    startTimer = System.nanoTime();

                    break;
                case "traverse": //go through the entire tree and declare how long it takes to traverse
                    break;
                case "remove": //measure how long it takes to remove the entire tree, this is the last switch for an obvious
                    break;
                default: //if none of these strings are met, the program will automatically end via default
                    break;
            }
        }
    }
}
