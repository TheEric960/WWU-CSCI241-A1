package sort;

import java.util.Random;
import java.util.Scanner;

public class SortsDriver {

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Sorts sorts = new Sorts();

        // get sorting method and array size
        System.out.print("Enter sort (i[nsertion], q[uick], m[erge], r[adix], a[ll]: ");
        String choice = sc.nextLine().trim().toLowerCase();
        System.out.print("Enter n (size of array to sort): ");
        int n = sc.nextInt();
        sc.nextLine();  // clear scanner

        // generate arrays
        int[] arrO = generateArray(n);
        int[] arrB = arrO.clone();

        printArray(arrO, n, "Unsorted");
        double time= 0;

        // sort based on input
        switch (choice) {
            case "i":
                time = System.nanoTime();
                sorts.insertionSort(arrB, 0, arrB.length);
                time = (System.nanoTime() - time) / 1000;
                printArray(arrB, n, "Sorted");
                System.out.println("Comparisons: " + sorts.getComparisonCount());
                System.out.println("Time(\u00B5s): " + time);
                break;
            case "q":
                time = System.nanoTime();
                sorts.quickSort(arrB, 0, arrB.length);
                time = (System.nanoTime() - time) / 1000;
                printArray(arrB, n, "Sorted");
                System.out.println("Comparisons: " + sorts.getComparisonCount());
                System.out.println("Time(\u00B5s): " + time);
                break;
            case "m":
                time = System.nanoTime();
                sorts.mergeSort(arrB, 0, arrB.length);
                time = (System.nanoTime() - time) / 1000;
                printArray(arrB, n, "Sorted");
                System.out.println("Comparisons: " + sorts.getComparisonCount());
                System.out.println("Time(\u00B5s): " + time);
                break;
            case "r":
//                time = System.nanoTime();
//                sorts.radixSort(arrB);
//                time = (System.nanoTime() - time) / 1000;
//                printArray(arrB, n, "Sorted");
//                System.out.println("Comparisons: " + sorts.getComparisonCount());
//                System.out.println("Time(\u00B5s): " + time);
//                break;
            case "a":
                time = System.nanoTime();
                sorts.insertionSort(arrB, 0, arrB.length);
                time = (System.nanoTime() - time) / 1000;
                System.out.println("insertion: " + sorts.getComparisonCount());
                printArray(arrB, n, "Sorted");
                System.out.println("Time(\u00B5s): " + time);
                sorts.resetComparisonCount();

                int[] arrC = arrO.clone();
                time = System.nanoTime();
                sorts.quickSort(arrC, 0, arrC.length);
                time = (System.nanoTime() - time) / 1000;
                System.out.println("\nquick: " + sorts.getComparisonCount());
                printArray(arrC, n, "Sorted");
                System.out.println("Time(\u00B5s): " + time);
                sorts.resetComparisonCount();

                int[] arrD = arrO.clone();
                time = System.nanoTime();
                sorts.mergeSort(arrD, 0, arrD.length);
                time = (System.nanoTime() - time) / 1000;
                System.out.println("\nmerge: " + sorts.getComparisonCount());
                printArray(arrD, n, "Sorted");
                System.out.println("Time(\u00B5s): " + time);
                sorts.resetComparisonCount();

//                int[] arrE = arrO.clone();
//                time = System.nanoTime();
//                sorts.radixSort(arrE);
//                time = (System.nanoTime() - time) / 1000;
//                System.out.println("\nradix: " + sorts.getComparisonCount());
//                System.out.println("Time(\u00B5s): " + time);
//                printArray(arrE, n, "Sorted");

                break;
            default:
                System.out.println("Invalid input, exiting...");
                sc.close();
                System.exit(0);
                break;
        }

        sc.close();
    }

    /**
     * Prints an array in format
     * <blockquote><pre>
     *      keyword: [ n1 n2 n3 ]
     * </pre></blockquote>
     * @param A array to be printed
     * @param n array length
     * @param keyword header for printed array
     */
    private static void printArray(int[] A, int n, String keyword) {
        if (n <= 20) {
            System.out.print(keyword + ": [ ");
            for (int a : A) {
                System.out.print(a + " ");
            }
            System.out.println("]");
        }
    }

    public static int[] generateArray(int n){
        System.out.print("Enter array type (r[andom], s[orted], m[ostly sorted], " +
                "p[robability sort]: ");
        String choice = sc.nextLine().trim().toLowerCase();

        switch (choice) {
            case "r":
                return getRandomArray(n);
            case "s":
                return getSortedArray(n);
            case "m":
                return getMostlySortedArray(n);
            case "p":
                return getProbabilityArray(n);
            default:
                System.out.println("Invalid input, generating random...");
                return getRandomArray(n);
        }
    }

    /**
     * generates a random array
     */
    private static int[] getRandomArray(int n) {
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = rand.nextInt(2 * n + 1) - n;
        }
        return A;
    }

    /**
     * generates a sorted array from [0...n]
     */
    private static int[] getSortedArray(int n) {
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = i;
        }

        return A;
    }

    /**
     * generates an array with the last 5% of its values being random
     */
    private static int[] getMostlySortedArray(int n) {
        int piv = n - (int)(.05 * n);
        int[] A = getSortedArray(n);
        int[] B = getRandomArray(n);

        int j = 0;
        for (int i = piv; i < n; i++) {
            A[i] = B[j];
            j++;
        }

        return A;
    }

    /**
     * generates a sorted array with every entry having a 10% chance of being random
     */
    private static int[] getProbabilityArray(int n) {
        int[] A = getSortedArray(n);
        int[] B = getRandomArray(n);

        for (int i = 0; i < n; i++) {
            double j = rand.nextDouble();
            if (j > 0.9) {
                A[i] = B[i];
            }
        }

        return A;
    }
}
