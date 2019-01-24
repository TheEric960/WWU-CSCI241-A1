
package sort;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Sorts {

    // maintains a count of comparisons performed by this Sorts object
    private int comparisonCount;

    public int getComparisonCount() {
        return comparisonCount;
    }

    public void resetComparisonCount() {
        comparisonCount = 0;
    }

    /**
     * Sorts A[start..end] in place using insertion sort
     * Precondition: 0 <= start <= end <= A.length
     */
    public void insertionSort(int[] A, int start, int end) {
        for (int i = 1; i < A.length; i++) {
            int j = i;

            while (j > 0 && A[j - 1] > A[j]) {
                comparisonCount++;
                swap(A, j, j - 1);
                j--;
            }

            comparisonCount++;  // for comparison failure
        }
    }

    /**
     * Partitions A[start..end] around the pivot A[pivIndex]; returns the
     * pivot's new index.
     * Precondition: start <= pivIndex < end
     * Postcondition: If partition returns i, then
     * A[start..i] <= A[i] <= A[i+1..end]
     **/
    public int partition(int[] A, int start, int end, int pivIndex) {
        swap(A, pivIndex, start);  // get pivot to start for partitioning
        int j = start + 1;

        for (int i = start + 1; i < end; i++) {
            comparisonCount++;
            if (A[i] < A[start]) {
                swap(A, j, i);
                j++;
            }
        }

        swap(A, start, j - 1);
        return j - 1;
    }

    /**
     * use quicksort to sort the subarray A[start..end]
     */
    public void quickSort(int[] A, int start, int end) {
        if (end - start == 0) return;
        int mid = partition(A, start, end, start);
        quickSort(A, start, mid);
        quickSort(A, mid + 1, end);
    }

    /**
     * merge the sorted subarrays A[start..mid] and A[mid..end] into
     * a single sorted array in A.
     */
    public void merge(int[] A, int start, int mid, int end) {
        int[] B = A.clone();
        int i = start, j = mid, k = start;

        while (i < mid && j < end) {
            comparisonCount++;

            if (B[i] < B[j]) {
                A[k] = B[i];
                i++;
            } else {
                A[k] = B[j];
                j++;
            }
            k++;
        }
        comparisonCount++;  // for comparison failure

        while (i < mid) {
            A[k] = B[i];
            i++;
            k++;
        }

        while (j < end) {
            A[k] = B[j];
            j++;
            k++;
        }
    }

    /**
     * use mergesort to sort the subarray A[start..end]
     */
    public void mergeSort(int[] A, int start, int end) {
        if (end - start < 2) return;
        int mid = (end - start) / 2 + start;

        mergeSort(A, start, mid);
        mergeSort(A, mid, end);

        merge(A, start, mid, end);
    }

    /**
     * Sort A using LSD radix sort. Uses queue buckets to sort.
     */
    public void radixSort(int[] A) {
        // find most significant digit
        int sigDigits = 0;
        for (int a : A) {
            if (Math.abs(a) > sigDigits) {
                sigDigits = Math.abs(a);
            }
        }

        // find position of significant digit (i.e. ones, tens, etc)
        int place = 1;  // ones place
        while ((sigDigits) > 9) {
            place++;
            sigDigits /= 10;
        }

        // sort for how significant the largest digit is
        for (int i = 0; i < place; i++) {
            queueSorting(A, i);
        }

        // sort negatives
        Stack<Integer> n = new Stack<>();
        Queue<Integer> p = new ArrayDeque<>();

        for (int a : A) {
            if (a < 0) n.push(a);
            else p.add(a);
        }

        // empty in order
        int i = 0;

        while (!n.isEmpty()) {
            A[i] = n.pop();
            i++;
        }

        while (!p.isEmpty()) {
            A[i] = p.remove();
            i++;
        }
    }

    /**
     * Assigns array numbers to queues based on the position of a digit and
     * unpacks them in ascending order.
     * @param A array of integers
     * @param place digit position being sorted
     */
    private void queueSorting(int[] A, int place) {
        // create queues
        Queue<Integer>[] queues = new Queue[10];
        for (int i = 0; i < 10; i++) {
            queues[i] = new ArrayDeque<>();
        }

        // fill queues based on digit value
        for (int a : A) {
            int n = getDigit(Math.abs(a), place);   // get digit for queue
            queues[n].add(a);
        }

        // empty the queues in order
        int j = 0;
        for (int i = 0; i < 10; i++) {
            while (!queues[i].isEmpty()) {
                A[j] = queues[i].remove();
                j++;
            }
        }


    }

    /* return the 10^d's place digit of n */
    private int getDigit(int n, int d) {
        return (n / ((int) Math.pow(10, d))) % 10;
    }

    /**
     * swap a[i] and a[j]
     * pre: 0 < i, j < a.size
     * post: values in a[i] and a[j] are swapped
     */
    public void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
