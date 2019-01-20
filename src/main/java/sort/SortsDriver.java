package sort;

import java.util.Random;
import java.util.Scanner;

public class SortsDriver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sorts sorts = new Sorts();

        System.out.print("Enter sort (i[nsertion], q[uick], m[erge], r[adix], a[ll]: ");
        String choice = sc.nextLine().trim().toLowerCase();
        System.out.print("Enter n (size of array to sort): ");
        int n = sc.nextInt();

        int[] arrO = getRandomArray(n);
        int[] arrB = arrO.clone();

        if (n <= 20) {
            printArray(arrO, n, "Unsorted");
        }

        switch (choice) {
            case "i":
                sorts.insertionSort(arrB, 0, arrB.length);
                printArray(arrB, n, "Sorted");
                System.out.println("Comparisons: " + sorts.getComparisonCount());
                break;
            case "q":
                sorts.quickSort(arrB, 0, arrB.length);
                printArray(arrB, n, "Sorted");
                System.out.println("Comparisons: " + sorts.getComparisonCount());
                break;
            case "m":
                sorts.mergeSort(arrB, 0, arrB.length);
                printArray(arrB, n, "Sorted");
                System.out.println("Comparisons: " + sorts.getComparisonCount());
                break;
            case "r":
//                sorts.radixSort(arrB);
//                printArray(arrB, n, "Sorted");
//                System.out.println("Comparisons: " + sorts.getComparisonCount());
//                break;
            case "a":
                sorts.insertionSort(arrB, 0, arrB.length);
                System.out.println("insertion: " + sorts.getComparisonCount());
                printArray(arrB, n, "Sorted");
                sorts.resetComparisonCount();

                int[] arrC = arrO.clone();
                sorts.quickSort(arrC, 0, arrC.length);
                System.out.println("\nquick: " + sorts.getComparisonCount());
                printArray(arrC, n, "Sorted");
                sorts.resetComparisonCount();

                int[] arrD = arrO.clone();
                sorts.mergeSort(arrD, 0, arrD.length);
                System.out.println("\nmerge: " + sorts.getComparisonCount());
                printArray(arrD, n, "Sorted");;
                sorts.resetComparisonCount();

//                int[] arrE = arrO.clone();
//                sorts.radixSort(arrE);
//                System.out.println("\nradix: " + sorts.getComparisonCount());
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

    private static void printArray(int[] A, int n, String keyword) {
        if (n <= 20) {
            System.out.print(keyword + ": [ ");
            for (int a : A) {
                System.out.print(a + " ");
            }
            System.out.println("]");
        }
    }

    /** method taken from SortsTest */
    private static int[] getRandomArray(int n) {
        int[] A = new int[n];
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < n; i++) {
            A[i] = rand.nextInt(2*n+1) - n;
        }
        return A;
    }
}
