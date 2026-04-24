package project20280.tree.comparisons;

import project20280.tree.TreapMap;

import java.util.*;

/**
 * q3 sorting benchmark
 *
 * compares five sorting algorithms:
 *   TreapSort        — insert all into TreapMap, retrieve via in-order traversal
 *   PQSort           — insert all into java.util.PriorityQueue, poll everything out
 *   Collections.sort — Java's TimSort (via List<Integer>)
 *   QuickSort        — in-place, median-of-three pivot
 *   MergeSort        — top-down, O(n log n) guaranteed
 *
 * input patterns: random, nearly sorted, reverse sorted
 * sizes: 100, 500, 1000, 5000, 10000
 */
public class SortingBenchmark {

    private static final int   WARMUP_RUNS = 3;
    private static final int   TIMED_RUNS  = 7;
    private static final int[] SIZES       = {100, 500, 1000, 5000, 10000};

    // TreapSort - extract items via in-order traversal - O(n log n)
    static int[] sortWithTreap(int[] input) {
        TreapMap<Integer, Integer> treap = new TreapMap<>();
        for (int v : input) treap.put(v, v);
        List<Integer> out = new ArrayList<>(input.length);
        for (Integer k : treap.keySet()) out.add(k);
        int[] result = new int[out.size()];
        for (int i = 0; i < result.length; i++) result[i] = out.get(i);
        return result;
    }

    // PQSort - min-heap PQ, then poll in-order - O(n log n)
    static int[] sortWithPQ(int[] input) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(input.length);
        for (int v : input) pq.offer(v);
        int[] result = new int[input.length];
        for (int i = 0; i < result.length; i++) result[i] = pq.poll();
        return result;
    }

    // Collections.sort - hybrid merge/insertion sort - Time dependent on how sorted input is - O(n log n) worst case,
    // O(n) on nearly sorted array
    static int[] sortWithCollections(int[] input) {
        List<Integer> list = new ArrayList<>(input.length);
        for (int v : input) list.add(v);
        Collections.sort(list);
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) result[i] = list.get(i);
        return result;
    }

    // QuickSort - in-place median of three pivot selection. O(n log n) average, O(n^2) worst case
    static int[] sortWithQuickSort(int[] input) {
        int[] arr = Arrays.copyOf(input, input.length);
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    // MergeSort - top down recursive sorting, O(n log n) guaranteed
    static int[] sortWithMergeSort(int[] input) {
        int[] arr = Arrays.copyOf(input, input.length);
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    // QuickSort helpers

    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int p = partition(arr, lo, hi);
            quickSort(arr, lo, p - 1);
            quickSort(arr, p + 1, hi);
        }
    }

    private static int partition(int[] arr, int lo, int hi) {
        // Median-of-three: pick pivot as median of arr[lo], arr[mid], arr[hi]
        // and place it at arr[hi] before partitioning.
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] < arr[lo]) swap(arr, lo, mid);
        if (arr[hi]  < arr[lo]) swap(arr, lo, hi);
        if (arr[mid] < arr[hi]) swap(arr, mid, hi);
        // arr[hi] is now the median — use as pivot
        int pivot = arr[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (arr[j] <= pivot) { i++; swap(arr, i, j); }
        }
        swap(arr, i + 1, hi);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    // MergeSort helpers

    private static void mergeSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        int[] left  = Arrays.copyOfRange(arr, lo, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, hi + 1);
        int i = 0, j = 0, k = lo;
        while (i < left.length && j < right.length)
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        while (i < left.length)  arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    @FunctionalInterface
    interface Sorter { int[] sort(int[] input); }

    // returns avg execution time in ns
    static long timeSort(Sorter s, int[] data) {
        for (int i = 0; i < WARMUP_RUNS; i++) s.sort(data);
        long total = 0;
        for (int i = 0; i < TIMED_RUNS; i++) {
            long start = System.nanoTime();
            s.sort(data);
            total += System.nanoTime() - start;
        }
        return total / TIMED_RUNS;
    }

    // for space complexity - gets the heap delta in bytes (change in amount of memory free before->after sort).
    static long memSort(Sorter s, int[] data) {
        System.gc();
        Runtime rt = Runtime.getRuntime();
        long before = rt.totalMemory() - rt.freeMemory();
        s.sort(data);
        long after = rt.totalMemory() - rt.freeMemory();
        return Math.max(0, after - before);
    }

    static int[] generate(int n, String pattern, Random rnd) {
        int[] d = new int[n];
        switch (pattern) {
            case "random":
                // Fisher-Yates shuffle to guarantee random array
                for (int i = 0; i < n; i++) d[i] = i;
                for (int i = n - 1; i > 0; i--) {
                    int j = rnd.nextInt(i + 1);
                    swap(d, i, j);
                }
                break;
            case "nearly_sorted":
                // sorted, then ~5% are swapped around
                for (int i = 0; i < n; i++) d[i] = i;
                int swaps = Math.max(1, n / 20);
                for (int i = 0; i < swaps; i++) {
                    int a = rnd.nextInt(n), b = rnd.nextInt(n);
                    swap(d, a, b);
                }
                break;
            case "reverse_sorted":
                // sorted descending - worst case for some of these sorts
                for (int i = 0; i < n; i++) d[i] = n - i;
                break;
            default:
                throw new IllegalArgumentException("Unknown pattern: " + pattern);
        }
        return d;
    }

    // everything above coded by hand - below, for formatting etc, Claude was used to save time (we all hate print
    // statement formatting)
    public static void main(String[] args) {
        Random rnd = new Random(42); // fixed seed — rerun gives identical data

        String[] algoNames = {
                "TreapSort",
                "PQSort",
                "Collections.sort",
                "QuickSort",
                "MergeSort"
        };
        Sorter[] sorters = {
                SortingBenchmark::sortWithTreap,
                SortingBenchmark::sortWithPQ,
                SortingBenchmark::sortWithCollections,
                SortingBenchmark::sortWithQuickSort,
                SortingBenchmark::sortWithMergeSort
        };

        // checks that all sorting algorithms sort correctly
        System.out.println("=== Correctness check ===");
        int[] sample   = {5, 3, 8, 1, 9, 2, 7, 4, 6, 0};
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < algoNames.length; i++) {
            int[] result = sorters[i].sort(sample);
            boolean ok = Arrays.equals(result, expected);
            System.out.printf("  %-20s %s  [%s]%n",
                    algoNames[i], Arrays.toString(result), ok ? "PASS" : "FAIL");
        }
        System.out.println();

        // benchmarking
        String[] patterns = {"random", "nearly_sorted", "reverse_sorted"};

        for (String pattern : patterns) {
            System.out.printf("=== Pattern: %-15s ===================================%n", pattern);
            System.out.printf("%-10s  %-20s  %16s  %16s%n",
                    "n", "Algorithm", "Avg time (ns)", "Heap delta (B)");
            System.out.println("-".repeat(70));

            for (int n : SIZES) {
                int[] data = generate(n, pattern, rnd);
                System.out.println();
                for (int i = 0; i < algoNames.length; i++) {
                    long t   = timeSort(sorters[i], data);
                    long mem = memSort(sorters[i], data);
                    System.out.printf("%-10d  %-20s  %16d  %16d%n",
                            n, algoNames[i], t, mem);
                }
            }
            System.out.println();
        }

        // space complexity summary - analytical
        System.out.println("=== Analytical space complexity ===");
        System.out.printf("  %-20s  %s%n", "TreapSort",        "O(n)    — tree nodes (~64 B each)");
        System.out.printf("  %-20s  %s%n", "PQSort",           "O(n)    — backing array");
        System.out.printf("  %-20s  %s%n", "Collections.sort", "O(n)    — List<Integer> boxing + TimSort merge buffer");
        System.out.printf("  %-20s  %s%n", "QuickSort",        "O(log n) — in-place, stack only");
        System.out.printf("  %-20s  %s%n", "MergeSort",        "O(n)    — temporary arrays in merge()");
    }
}
